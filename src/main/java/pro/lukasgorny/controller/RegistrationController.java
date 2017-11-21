package pro.lukasgorny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pro.lukasgorny.dto.UserDto;
import pro.lukasgorny.enums.RoleEnum;
import pro.lukasgorny.enums.Templates;
import pro.lukasgorny.event.OnRegistrationCompleteEvent;
import pro.lukasgorny.model.User;
import pro.lukasgorny.model.VerificationToken;
import pro.lukasgorny.service.helper.HelperServiceImpl;
import pro.lukasgorny.service.registration.RegistrationService;
import pro.lukasgorny.service.user.UserService;

import javax.validation.Valid;

/**
 * Created by Łukasz on 24.10.2017.
 */
@Controller
public class RegistrationController {

    private final UserService userService;
    private final HelperServiceImpl helperService;
    private final RegistrationService registrationService;
    private final ApplicationEventPublisher eventPublisher;
    private final MessageSource messageSource;

    @Autowired
    public RegistrationController(UserService userService, HelperServiceImpl helperService,
                                  RegistrationService registrationService, ApplicationEventPublisher eventPublisher,
                                  MessageSource messageSource) {
        this.userService = userService;
        this.helperService = helperService;
        this.registrationService = registrationService;
        this.eventPublisher = eventPublisher;
        this.messageSource = messageSource;
    }

    @GetMapping("/register")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userDto", new UserDto());
        modelAndView.addObject("months", helperService.prepareMonthsList());
        modelAndView.setViewName(Templates.REGISTRATION);
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView createNewUser(@Valid UserDto userDto, BindingResult bindingResult, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("months", helperService.prepareMonthsList());

        User userExists = userService.getByEmail(userDto.getEmail());

        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user.exists");
        }

        if (!registrationService.validateEmail(userDto.getEmail())) {
            bindingResult.rejectValue("email", "error.email.invalid.format");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(Templates.REGISTRATION);
        } else {
            userDto.getRoles().add(RoleEnum.USER);
            User user = registrationService.register(userDto);
            modelAndView.setViewName(Templates.REGISTRATION_SUCCESS);
            modelAndView.addObject("userDto", userDto);

            try {
                String appUrl = request.getContextPath();
                eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                        (user, request.getLocale(), appUrl));
            } catch (Exception me) {
                me.printStackTrace();
                modelAndView.setViewName(Templates.EMAIL_ERROR);
            }
        }

        return modelAndView;
    }

    @GetMapping("/activate")
    public ModelAndView activate(@RequestParam("token") String token, RedirectAttributes redirectAttributes) {
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null || token == null) {
            redirectAttributes.addAttribute("message", messageSource.getMessage("error.message.invalid.token", null, LocaleContextHolder.getLocale()));
            return new ModelAndView("redirect:/token-error");
        }

        ModelAndView modelAndView = new ModelAndView("activation-success");
        User user = verificationToken.getUser();

        /*Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messageSource.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", messageValue);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }*/

        user.setEnabled(true);
        userService.save(user);

        return modelAndView;
    }

    @GetMapping("/token-error")
    public ModelAndView tokenError(@ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(Templates.TOKEN_ERROR);
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParams(MissingServletRequestParameterException ex, RedirectAttributes redirectAttributes) {
        String name = ex.getParameterName();

        if (name.equals("token")) {
            redirectAttributes.addAttribute("message", messageSource.getMessage("error.message.invalid.token", null, LocaleContextHolder.getLocale()));
            return "redirect:/token-error";
        }

        return "redirect:/login";
    }
}
