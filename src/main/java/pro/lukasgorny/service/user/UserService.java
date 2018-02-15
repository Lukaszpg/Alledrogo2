package pro.lukasgorny.service.user;

import pro.lukasgorny.dto.user.ChangeEmailDto;
import pro.lukasgorny.dto.user.ChangePasswordDto;
import pro.lukasgorny.dto.user.UserExtendedDto;
import pro.lukasgorny.dto.user.UserResultDto;
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
}
