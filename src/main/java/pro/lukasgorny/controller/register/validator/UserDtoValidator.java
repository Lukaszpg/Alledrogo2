package pro.lukasgorny.controller.register.validator;

import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pro.lukasgorny.dto.UserSaveDto;
import pro.lukasgorny.model.User;
import pro.lukasgorny.service.registration.RegistrationService;
import pro.lukasgorny.service.user.UserService;

@Component
public class UserDtoValidator implements Validator {

    private UserService userService;
    private RegistrationService registrationService;

    @Autowired
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

        if(userSaveDto.getPassword().length() < 8 || userSaveDto.getPassword().length() > 40) {
            errors.rejectValue("password", "Size.userSaveDto.password");
        }

        if(userSaveDto.getBirthdayDay().length() < 1 || userSaveDto.getBirthdayDay().length() > 2) {
            errors.rejectValue("birthdayDay", "Size.userSaveDto.birthdayDay");
        }

        if(StringUtils.isNullOrEmpty(userSaveDto.getBirthdayMonth())) {
            errors.rejectValue("birthdayMonth", "NotNull.userSaveDto.birthdayMonth");
        }

        if(userSaveDto.getBirthdayYear().length() < 4 || userSaveDto.getBirthdayYear().length() > 4) {
            errors.rejectValue("birthdayYear", "Size.userSaveDto.birthdayYear");
        }
    }
}
