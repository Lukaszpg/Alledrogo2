package pro.lukasgorny.dto;

import pro.lukasgorny.enums.RoleEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Łukasz on 24.10.2017.
 */
public class UserDto {

    @NotNull
    private String email;

    @NotNull
    @Size(min = 8, max = 40)
    private String password;

    @NotNull
    @Size(min = 1, max = 2)
    private String birthdayDay;

    @NotNull
    private String birthdayMonth;

    @NotNull
    @Size(min = 4, max = 4)
    private String birthdayYear;

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
}
