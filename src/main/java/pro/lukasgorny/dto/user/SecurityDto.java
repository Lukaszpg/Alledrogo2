package pro.lukasgorny.dto.user;

import javax.validation.constraints.NotNull;

/**
 * Created by lukaszgo on 2018-02-26.
 */
public class SecurityDto {

    private String email;
    private boolean using2fa;

    @NotNull
    private String actualPassword;

    public boolean getUsing2fa() {
        return using2fa;
    }

    public void setUsing2fa(boolean using2fa) {
        this.using2fa = using2fa;
    }

    public String getActualPassword() {
        return actualPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
