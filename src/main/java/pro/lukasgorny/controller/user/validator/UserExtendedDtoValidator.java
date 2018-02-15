package pro.lukasgorny.controller.user.validator;

import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pro.lukasgorny.dto.user.UserExtendedDto;

/**
 * Created by Łukasz on 09.02.2018.
 */
@Component
public class UserExtendedDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserExtendedDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserExtendedDto userExtendedDto = (UserExtendedDto) o;

        if(StringUtils.isNullOrEmpty(userExtendedDto.getFirstName())) {
            errors.rejectValue("firstName", "error.userExtendedDto.firstName");
        }

        if(StringUtils.isNullOrEmpty(userExtendedDto.getSurname())) {
            errors.rejectValue("surname", "error.userExtendedDto.surname");
        }

        if(StringUtils.isNullOrEmpty(userExtendedDto.getAddress())) {
            errors.rejectValue("address", "error.userExtendedDto.address");
        }

        if(StringUtils.isNullOrEmpty(userExtendedDto.getZipCode())) {
            errors.rejectValue("zipCode", "error.userExtendedDto.zipCode.null");
        } else if(!StringUtils.isNullOrEmpty(userExtendedDto.getZipCode()) && userExtendedDto.getZipCode().length() < 6) {
            errors.rejectValue("zipCode", "error.userExtendedDto.zipCode.size");
        }

        if(StringUtils.isNullOrEmpty(userExtendedDto.getCity())) {
            errors.rejectValue("city", "error.userExtendedDto.city");
        }

        if(userExtendedDto.getVoivodeship() == null) {
            errors.rejectValue("voivodeship", "error.userExtendedDto.voivodeship");
        }

        if(StringUtils.isNullOrEmpty(userExtendedDto.getFirstPhoneNumber())) {
            errors.rejectValue("firstPhoneNumber", "error.userExtendedDto.firstPhoneNumber.null");
        } else if(!StringUtils.isNullOrEmpty(userExtendedDto.getFirstPhoneNumber()) && userExtendedDto.getFirstPhoneNumber().length() < 9) {
            errors.rejectValue("firstPhoneNumber", "error.userExtendedDto.firstPhoneNumber.size");
        }
    }
}
