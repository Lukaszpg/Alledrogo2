package pro.lukasgorny.controller.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pro.lukasgorny.service.paycheck.CreatePaycheckService;
import pro.lukasgorny.util.Templates;
import pro.lukasgorny.util.Urls;

/**
 * Created by Łukasz on 28.02.2018.
 */
@Controller
@RequestMapping(Urls.Payment.MAIN)
public class PaymentController {

    @Autowired
    private CreatePaycheckService createPaycheckService;

    @GetMapping(Urls.Payment.CONFIRM)
    public ModelAndView confirm(@RequestParam("PayerID") String payerId, @RequestParam("paymentId") String paymentId){
        ModelAndView modelAndView = new ModelAndView(Templates.PaymentTemplates.CONFIRM);
        modelAndView.addObject("payerId", payerId);
        modelAndView.addObject("paymentId", paymentId);
        return modelAndView;
    }

    @GetMapping(Urls.Payment.ERROR)
    public ModelAndView error(){
        return new ModelAndView(Templates.PaymentTemplates.ERROR);
    }

    @GetMapping(Urls.Payment.COMPLETE)
    public ModelAndView completeSuccess() {
        return new ModelAndView(Templates.PaymentTemplates.COMPLETE);
    }

    @GetMapping(Urls.Payment.CANCEL)
    public ModelAndView cancel(@RequestParam("token") String token) {
        ModelAndView modelAndView = new ModelAndView(Templates.PaymentTemplates.CANCEL);
        createPaycheckService.cancel(token);
        return modelAndView;
    }
}
