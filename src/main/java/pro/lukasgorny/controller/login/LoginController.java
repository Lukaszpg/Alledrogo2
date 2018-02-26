package pro.lukasgorny.controller.login;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;

import pro.lukasgorny.dto.user.TotpDto;
import pro.lukasgorny.service.user.TOTPVerificationService;
import pro.lukasgorny.service.user.UserService;
import pro.lukasgorny.util.Templates;
import pro.lukasgorny.util.Urls;

/**
 * Created by lukaszgo on 2017-05-25.
 */

@Controller
public class LoginController {

    private final TOTPVerificationService totpVerificationService;
    private final UserService userService;

    @Autowired
    public LoginController(TOTPVerificationService totpVerificationService, UserService userService) {
        this.totpVerificationService = totpVerificationService;
        this.userService = userService;
    }

    @GetMapping(Urls.Login.LOGIN)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(Templates.LOGIN);
        return modelAndView;
    }

    @GetMapping(Urls.Login.CODE)
    public ModelAndView code() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("totpDto", new TotpDto());
        modelAndView.setViewName(Templates.CODE);
        return modelAndView;
    }

    @PostMapping(Urls.Login.CODE)
    public ModelAndView codePost(TotpDto totpDto, BindingResult bindingResult, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        totpDto.setEmail(principal.getName());

        if(StringUtils.isNullOrEmpty(totpDto.getCode()) || !totpVerificationService.isCodeValid(totpDto)) {
            bindingResult.rejectValue("code", "error.fa.code.invalid");
        }

        if(bindingResult.hasErrors()) {
            modelAndView.setViewName(Templates.CODE);
            return modelAndView;
        }

        userService.grantAuthorities(principal.getName());
        modelAndView.setViewName(Urls.Index.HOME_REDIRECT);
        return modelAndView;
    }
}
