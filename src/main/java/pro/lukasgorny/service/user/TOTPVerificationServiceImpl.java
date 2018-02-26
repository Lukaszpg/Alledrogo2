package pro.lukasgorny.service.user;

import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.lukasgorny.dto.user.TotpDto;
import pro.lukasgorny.model.User;

/**
 * Created by lukaszgo on 2018-02-26.
 */
@Service
public class TOTPVerificationServiceImpl implements TOTPVerificationService {

    private final UserService userService;

    @Autowired
    public TOTPVerificationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public boolean isCodeValid(TotpDto totpDto) {
        User user = userService.getByEmail(totpDto.getEmail());
        Totp totp = new Totp(user.getSecret());

        return isValidLong(totpDto.getCode()) && totp.verify(totpDto.getCode());

    }

    private boolean isValidLong(String code) {
        try {
            Long.parseLong(code);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
