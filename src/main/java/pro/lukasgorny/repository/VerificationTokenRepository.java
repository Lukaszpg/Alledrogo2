package pro.lukasgorny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.lukasgorny.model.User;
import pro.lukasgorny.model.VerificationToken;

/**
 * Created by Łukasz on 24.10.2017.
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);
}
