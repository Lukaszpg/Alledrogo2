package pro.lukasgorny.controller.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.lukasgorny.service.payment.PaymentService;
import pro.lukasgorny.util.Urls;

import javax.servlet.http.HttpServletRequest;
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
    public Map<String, Object> makePayment(@PathVariable("transactionId") String transactionId){
        return paymentService.createPayment(transactionId);
    }

    @PostMapping(Urls.PaymentRest.COMPLETE)
    public Map<String, Object> completePayment(HttpServletRequest request){
        return paymentService.completePayment(request);
    }
}
