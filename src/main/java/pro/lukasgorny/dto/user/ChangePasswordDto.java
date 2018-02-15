package pro.lukasgorny.dto.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by lukaszgo on 2018-02-05.
 */
public class ChangePasswordDto {

    private String email;

    @NotNull
    private String actualPassword;

    @NotNull
    @Size(min = 8, max = 40)
    private String newPassword;

    public String getActualPassword() {
        return actualPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
