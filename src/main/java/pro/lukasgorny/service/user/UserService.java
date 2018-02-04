package pro.lukasgorny.service.user;

import pro.lukasgorny.dto.UserExtendedDto;
import pro.lukasgorny.dto.UserResultDto;
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
}
