package pro.lukasgorny.service.user;

import pro.lukasgorny.dto.user.TotpDto;

/**
 * Created by lukaszgo on 2018-02-26.
 */
public interface TOTPVerificationService {
    boolean isCodeValid(TotpDto totpDto);
}
