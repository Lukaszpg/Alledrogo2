package pro.lukasgorny.controller.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pro.lukasgorny.controller.register.validator.UserDtoValidator;
import pro.lukasgorny.dto.UserSaveDto;
import pro.lukasgorny.enums.RoleEnum;
import pro.lukasgorny.util.Templates;
import pro.lukasgorny.event.OnRegistrationCompleteEvent;
import pro.lukasgorny.model.User;
import pro.lukasgorny.model.VerificationToken;
import pro.lukasgorny.service.helper.HelperServiceImpl;
import pro.lukasgorny.service.registration.RegistrationService;
import pro.lukasgorny.service.user.UserService;
import pro.lukasgorny.util.Urls;

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

    @GetMapping(Urls.Registration.REGISTER)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userDto", new UserSaveDto());
        modelAndView.addObject("months", helperService.prepareMonthsList());
        modelAndView.setViewName(Templates.REGISTRATION);
        return modelAndView;
    }

    @PostMapping(Urls.Registration.REGISTER)
    public ModelAndView createNewUser(@Valid UserSaveDto userSaveDto, BindingResult bindingResult, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("months", helperService.prepareMonthsList());

        new UserDtoValidator(userService, registrationService).validate(userSaveDto, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(Templates.REGISTRATION);
        } else {
            User user = registerUser(userSaveDto);

            try {
                sendActivationEmail(user, request);
            } catch (Exception e) {
                modelAndView.setViewName(Urls.Registration.EMAIL_ERROR_REDIRECT);
                e.printStackTrace();
            }

            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("email", userSaveDto.getEmail());
            return new ModelAndView(Urls.Registration.REGISTER_SUCCESS_REDIRECT, modelMap);
        }

        return modelAndView;
    }

    private User registerUser(UserSaveDto userSaveDto) {
        userSaveDto.getRoles().add(RoleEnum.USER);
        registrationService.setUserSaveDto(userSaveDto);
        return registrationService.register();
    }

    private void sendActivationEmail(User user, WebRequest request) throws Exception {
        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                (user, request.getLocale(), appUrl));
    }

    @GetMapping(Urls.Registration.ACTIVATE)
    public ModelAndView activate(@RequestParam("token") String token, RedirectAttributes redirectAttributes) {
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null || token == null) {
            redirectAttributes.addAttribute("message", messageSource.getMessage("error.message.invalid.token", null, LocaleContextHolder.getLocale()));
            return new ModelAndView(Urls.Registration.TOKEN_ERROR_REDIRECT);
        }

        ModelAndView modelAndView = new ModelAndView(Templates.ACTIVATION_SUCCESS);
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

    @GetMapping(Urls.Registration.TOKEN_ERROR)
    public ModelAndView tokenError(@ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(Templates.TOKEN_ERROR);
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @GetMapping(Urls.Registration.REGISTER_SUCCESS)
    public ModelAndView registerSuccess(@ModelAttribute("email") String email) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(Templates.REGISTRATION_SUCCESS);
        modelAndView.addObject("email", email);
        return modelAndView;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParams(MissingServletRequestParameterException ex, RedirectAttributes redirectAttributes) {
        String name = ex.getParameterName();

        if (name.equals("token")) {
            redirectAttributes.addAttribute("message", messageSource.getMessage("error.message.invalid.token", null, LocaleContextHolder.getLocale()));
            return Urls.Registration.TOKEN_ERROR_REDIRECT;
        }

        return Urls.Login.LOGIN_REDIRECT;
    }
}
