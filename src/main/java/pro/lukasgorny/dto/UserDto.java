package pro.lukasgorny.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 2, max = 2)
    private String day;

    @NotNull
    private String month;

    @NotNull
    @Size(min = 4, max = 4)
    private String year;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
