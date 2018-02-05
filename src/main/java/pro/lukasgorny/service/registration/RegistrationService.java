package pro.lukasgorny.service.registration;

import pro.lukasgorny.dto.ResetPasswordDto;
import pro.lukasgorny.dto.UserSaveDto;
import pro.lukasgorny.model.User;

/**
 * Created by Łukasz on 24.10.2017.
 */
public interface RegistrationService {

    User register();
    boolean validateEmail(String email);
    void setUserSaveDto(UserSaveDto userSaveDto);
    boolean resetUserPassword(ResetPasswordDto resetPasswordDto);
    ResetPasswordDto generateAndSaveUserNewPassword(ResetPasswordDto resetPasswordDto);
}
