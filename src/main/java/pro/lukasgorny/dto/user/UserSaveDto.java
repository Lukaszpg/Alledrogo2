package pro.lukasgorny.dto.user;

import java.util.ArrayList;
import java.util.List;

import pro.lukasgorny.enums.RoleEnum;

/**
 * Created by Łukasz on 24.10.2017.
 */
public class UserSaveDto {

    private String email;
    private String password;
    private String birthdayDay;
    private String birthdayMonth;
    private String birthdayYear;
    private boolean using2fa;

    private List<RoleEnum> roles = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdayMonth() {
        return birthdayMonth;
    }

    public void setBirthdayMonth(String birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }

    public String getBirthdayDay() {
        return birthdayDay;
    }

    public void setBirthdayDay(String birthdayDay) {
        this.birthdayDay = birthdayDay;
    }

    public String getBirthdayYear() {
        return birthdayYear;
    }

    public void setBirthdayYear(String birthdayYear) {
        this.birthdayYear = birthdayYear;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }

    public boolean getUsing2fa() {
        return using2fa;
    }

    public void setUsing2fa(boolean using2fa) {
        this.using2fa = using2fa;
    }
}
