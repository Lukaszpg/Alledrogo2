package pro.lukasgorny.controller.payment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pro.lukasgorny.util.Urls;

/**
 * Created by Łukasz on 28.02.2018.
 */
@Controller
@RequestMapping(Urls.Payment.MAIN)
public class PaymentController {

    @GetMapping(Urls.Payment.COMPLETE)
    public ModelAndView completePayment(@RequestParam("PayerID") String payerId, @RequestParam("paymentId") String paymentId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("payerId", payerId);
        modelAndView.addObject("paymentId", paymentId);
        return modelAndView;
    }
}
