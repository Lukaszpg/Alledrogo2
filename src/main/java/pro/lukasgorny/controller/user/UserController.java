package pro.lukasgorny.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pro.lukasgorny.util.Templates;
import pro.lukasgorny.util.Urls;

import java.security.Principal;

/**
 * Created by Łukasz on 25.10.2017.
 */

@Controller
@RequestMapping(Urls.User.MAIN)
public class UserController {

    @GetMapping(Urls.User.ACCOUNT)
    public ModelAndView account(Principal principal) {
        ModelAndView modelAndView = new ModelAndView(Templates.UserTemplates.ACCOUNT);
        return modelAndView;
    }
}
