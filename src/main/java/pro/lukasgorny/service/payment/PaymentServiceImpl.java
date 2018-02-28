package pro.lukasgorny.service.payment;

import com.paypal.api.payments.*;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.lukasgorny.dto.paycheck.PaycheckSaveDto;
import pro.lukasgorny.enums.PaycheckType;
import pro.lukasgorny.enums.TransactionType;
import pro.lukasgorny.model.Paycheck;
import pro.lukasgorny.service.auction.CreateTransactionService;
import pro.lukasgorny.service.auction.GetTransactionService;
import pro.lukasgorny.service.paycheck.CreatePaycheckService;
import pro.lukasgorny.service.paycheck.GetPaycheckService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Łukasz on 27.02.2018.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.currency}")
    private String currency;

    @Value("${paypal.cancel.url}")
    private String cancelUrl;

    @Value("${paypal.return.url}")
    private String returnUrl;

    @Value("${paypal.error.url}")
    private String errorUrl;

    @Value("${paypal.payment.method}")
    private String method;

    private final GetTransactionService getTransactionService;
    private final CreateTransactionService createTransactionService;
    private final CreatePaycheckService createPaycheckService;
    private final GetPaycheckService getPaycheckService;

    @Autowired
    public PaymentServiceImpl(GetTransactionService getTransactionService, CreateTransactionService createTransactionService, CreatePaycheckService createPaycheckService, GetPaycheckService getPaycheckService) {
        this.getTransactionService = getTransactionService;
        this.createTransactionService = createTransactionService;
        this.createPaycheckService = createPaycheckService;
        this.getPaycheckService = getPaycheckService;
    }

    @Override
    public Map<String, Object> createPayment(String transactionId) {
        Map<String, Object> response = new HashMap<>();

        pro.lukasgorny.model.Transaction auctionTransaction = getTransactionService.getOneEntity(transactionId);
        String sum = getSumFromAuctionTransaction(auctionTransaction);
        Payment payment = preparePayment(sum);
        Payment createdPayment;

        PaycheckSaveDto paycheckSaveDto = createPaycheckDto(auctionTransaction);

        try {
            String redirectUrl = "";
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            createdPayment = payment.create(context);
            if (createdPayment != null) {
                List<Links> links = createdPayment.getLinks();
                for (Links link : links) {
                    if (link.getRel().equals("approval_url")) {
                        redirectUrl = link.getHref();
                        break;
                    }
                }
                response.put("status", "success");
                response.put("redirect_url", redirectUrl);

                paycheckSaveDto.setPaycheckType(PaycheckType.IN_PROGRESS);

                paycheckSaveDto.setPaypalPaymentId(createdPayment.getId());
                createTransactionService.save(auctionTransaction);
            }
        } catch (PayPalRESTException e) {
            response.put("status", "error");
            response.put("redirect_url", errorUrl);
            paycheckSaveDto.setPaycheckType(PaycheckType.FAILED);
        }

        createPaycheckService.create(paycheckSaveDto);

        return response;
    }

    @Override
    public Map<String, Object> completePayment(HttpServletRequest req) {
        Map<String, Object> response = new HashMap();
        String payPalPaymentId = req.getParameter("paymentId");
        Payment payment = new Payment();
        payment.setId(payPalPaymentId);

        Paycheck paycheck = getPaycheckService.getByPayPalPaymentId(payPalPaymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(req.getParameter("PayerID"));

        try {
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            Payment createdPayment = payment.execute(context, paymentExecution);
            if (createdPayment != null) {
                response.put("status", "success");
                response.put("payment", createdPayment);

                paycheck.setType(PaycheckType.COMPLETED);
            }
        } catch (PayPalRESTException e) {
            response.put("status", "error");
            response.put("message", e.getDetails());

            paycheck.setType(PaycheckType.FAILED);
        }

        createPaycheckService.update(paycheck);

        return response;
    }

    private String getSumFromAuctionTransaction(pro.lukasgorny.model.Transaction auctionTransaction) {
        return TransactionType.BID.equals(auctionTransaction.getTransactionType())
                ? auctionTransaction.getOffer().toString()
                : auctionTransaction.getAuction().getPrice().toString();
    }

    private PaycheckSaveDto createPaycheckDto(pro.lukasgorny.model.Transaction auctionTransaction) {
        String sum = getSumFromAuctionTransaction(auctionTransaction);
        BigDecimal money = new BigDecimal(sum.replaceAll(",", ""));
        PaycheckSaveDto paycheckSaveDto = new PaycheckSaveDto();
        paycheckSaveDto.setCash(money);
        paycheckSaveDto = setDataFromTransaction(paycheckSaveDto, auctionTransaction);
        return paycheckSaveDto;
    }

    private PaycheckSaveDto setDataFromTransaction(PaycheckSaveDto paycheckSaveDto, pro.lukasgorny.model.Transaction auctionTransaction) {
        paycheckSaveDto.setAuction(auctionTransaction.getAuction());
        paycheckSaveDto.setPayer(auctionTransaction.getUser());

        return paycheckSaveDto;
    }

    private Payment preparePayment(String sum) {
        Transaction transaction = prepareTransaction(sum);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = preparePayer();

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = prepareRedirectUrls();
        payment.setRedirectUrls(redirectUrls);

        return payment;
    }

    private RedirectUrls prepareRedirectUrls() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(returnUrl);
        return redirectUrls;
    }

    private Transaction prepareTransaction(String sum) {
        Amount amount = prepareAmount(sum);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        return transaction;
    }

    private Amount prepareAmount(String sum) {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(sum);
        return amount;
    }

    private Payer preparePayer() {
        Payer payer = new Payer();
        payer.setPaymentMethod(method);
        return payer;
    }
}
