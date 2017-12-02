package pro.lukasgorny.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import pro.lukasgorny.util.Templates;
import pro.lukasgorny.util.Urls;

/**
 * Created by lukaszgo on 2017-05-25.
 */

@Controller
public class LoginController {

    @GetMapping(Urls.Login.LOGIN)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(Templates.LOGIN);
        return modelAndView;
    }
}
