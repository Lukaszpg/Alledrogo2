package pro.lukasgorny.controller.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pro.lukasgorny.dto.paycheck.PaymentCompleteDto;
import pro.lukasgorny.service.payment.PaymentService;
import pro.lukasgorny.util.Urls;

import java.util.Map;

/**
 * Created by Łukasz on 27.02.2018.
 */
@RestController
@RequestMapping(Urls.PaymentRest.MAIN)
public class PaymentRestController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(Urls.PaymentRest.CREATE)
    public Map<String, Object> makePayment(@PathVariable("transactionId") String transactionId) {
        return paymentService.createPayment(transactionId);
    }

    @PostMapping(Urls.PaymentRest.CONFIRM)
    public Map<String, Object> confirmRest(@RequestParam("payerId") String payerId, @RequestParam("paymentId") String paymentId) {
        PaymentCompleteDto paymentCompleteDto = new PaymentCompleteDto();
        paymentCompleteDto.setPayerId(payerId);
        paymentCompleteDto.setPaymentId(paymentId);
        return paymentService.completePayment(paymentCompleteDto);
    }
}
