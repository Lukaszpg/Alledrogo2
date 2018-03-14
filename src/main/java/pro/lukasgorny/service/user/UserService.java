package pro.lukasgorny.service.user;

import pro.lukasgorny.dto.user.*;
import pro.lukasgorny.model.User;
import pro.lukasgorny.model.VerificationToken;

/**
 * Created by lukaszgo on 2017-05-25.
 */
public interface UserService {

    void save(User user);
    User getByEmail(String email);
    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String VerificationToken);
    UserResultDto createDtoFromEntity(User user);
    UserExtendedDto getUserData(String email);
    void setAndSaveUserData(UserExtendedDto userExtendedDto);
    boolean isUserPasswordMatchWithInput(String inputPassword, String email);
    void changeUserPassword(ChangePasswordDto changePasswordDto);
    boolean isNewPasswordSameAsOldPassword(ChangePasswordDto changePasswordDto);
    void changeUserEmail(ChangeEmailDto changeEmailDto);
    User getById(Long id);
    User getById(String id);
    String generateQRUrl(User user);
    void grantAuthorities(String email);
    void changeSecurity(SecurityDto securityDto);
    UserResultDto getDtoByEmail(String email);
    UserResultDto getDtoById(String id);
}
