package pro.lukasgorny.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import pro.lukasgorny.util.Templates;

/**
 * Created by Łukasz on 25.10.2017.
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView(Templates.INDEX);
        return modelAndView;
    }
}
