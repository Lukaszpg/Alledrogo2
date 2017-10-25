package pro.lukasgorny.service.user;

import pro.lukasgorny.model.User;
import pro.lukasgorny.model.VerificationToken;

/**
 * Created by lukaszgo on 2017-05-25.
 */
public interface UserService {

    void save(User user);
    User findByEmail(String email);
    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String VerificationToken);
}
