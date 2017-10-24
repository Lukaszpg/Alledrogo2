package pro.lukasgorny.service.registration;

import pro.lukasgorny.dto.UserDto;
import pro.lukasgorny.model.User;

/**
 * Created by Łukasz on 24.10.2017.
 */
public interface RegistrationService {

    User register(UserDto userDto);
    boolean validateEmail(String email);
}
