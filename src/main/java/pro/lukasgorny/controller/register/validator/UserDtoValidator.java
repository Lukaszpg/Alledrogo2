package pro.lukasgorny.controller.register.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pro.lukasgorny.dto.UserSaveDto;
import pro.lukasgorny.model.User;
import pro.lukasgorny.service.registration.RegistrationService;
import pro.lukasgorny.service.user.UserService;

public class UserDtoValidator implements Validator {

    private UserService userService;
    private RegistrationService registrationService;

    public UserDtoValidator(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserSaveDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserSaveDto userSaveDto = (UserSaveDto) o;
        User userExists = userService.getByEmail(userSaveDto.getEmail());

        if (userExists != null) {
            errors.rejectValue("email", "error.user.exists");
        }

        if (!registrationService.validateEmail(userSaveDto.getEmail())) {
            errors.rejectValue("email", "error.email.invalid.format");
        }
    }
}
