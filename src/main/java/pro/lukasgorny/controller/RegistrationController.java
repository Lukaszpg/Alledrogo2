package pro.lukasgorny.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import pro.lukasgorny.dto.UserDto;
import pro.lukasgorny.enums.TemplatesEnum;
import pro.lukasgorny.event.OnRegistrationCompleteEvent;
import pro.lukasgorny.model.User;
import pro.lukasgorny.service.helper.HelperServiceImpl;
import pro.lukasgorny.service.registration.RegistrationService;
import pro.lukasgorny.service.user.UserService;

/**
 * Created by Łukasz on 24.10.2017.
 */
@Controller
public class RegistrationController {

    private final UserService userService;
    private final HelperServiceImpl helperService;
    private final RegistrationService registrationService;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public RegistrationController(UserService userService, HelperServiceImpl helperService, RegistrationService registrationService, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.helperService = helperService;
        this.registrationService = registrationService;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/register")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userDto", new UserDto());
        modelAndView.addObject("months", helperService.prepareMonthsList());
        modelAndView.setViewName(TemplatesEnum.REGISTRATION.getName());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView createNewUser(@Valid UserDto userDto, BindingResult bindingResult, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("months", helperService.prepareMonthsList());

        User userExists = userService.findByEmail(userDto.getEmail());

        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user.exists");
        }

        if(!registrationService.validateEmail(userDto.getEmail())) {
            bindingResult.rejectValue("email", "error.email.invalid.format");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(TemplatesEnum.REGISTRATION.getName());
        } else {
            User user = registrationService.register(userDto);
            modelAndView.setViewName(TemplatesEnum.REGISTRATION.getName());

            try {
                String appUrl = request.getContextPath();
                eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                        (user, request.getLocale(), appUrl));
            } catch (Exception me) {
                me.printStackTrace();
                return new ModelAndView(TemplatesEnum.EMAIL_ERROR.getName());
            }
        }
        return modelAndView;
    }
}
