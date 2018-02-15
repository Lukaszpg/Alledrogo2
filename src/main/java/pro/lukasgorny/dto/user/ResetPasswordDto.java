package pro.lukasgorny.dto.user;

import java.util.Locale;

/**
 * Created by lukaszgo on 2018-02-05.
 */
public class ResetPasswordDto {
    private String email;
    private String newPassword;
    private Locale locale;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
