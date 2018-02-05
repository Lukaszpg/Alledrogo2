package pro.lukasgorny.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by lukaszgo on 2018-02-05.
 */
public class ChangeEmailDto {

    @NotNull
    private String newEmail;

    @NotNull
    private String actualPassword;

    private String email;

    public String getActualPassword() {
        return actualPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
