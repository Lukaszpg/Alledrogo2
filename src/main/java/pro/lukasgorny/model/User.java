package pro.lukasgorny.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by lukaszgo on 2017-05-25.
 */
@Entity
@Table(name = "users")
public class User extends Model {
    private String email;
    private String password;
    private Boolean enabled;
    private Boolean blocked;
    private Boolean sellingBlocked;
    private List<Role> roles;
    private Integer birthdayDay;
    private Integer birthdayMonth;
    private Integer birthdayYear;

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getBirthdayDay() {
        return birthdayDay;
    }

    public void setBirthdayDay(Integer birthdayDay) {
        this.birthdayDay = birthdayDay;
    }

    public Integer getBirthdayMonth() {
        return birthdayMonth;
    }

    public void setBirthdayMonth(Integer birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }

    public Integer getBirthdayYear() {
        return birthdayYear;
    }

    public void setBirthdayYear(Integer birthdayYear) {
        this.birthdayYear = birthdayYear;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Boolean getSellingBlocked() {
        return sellingBlocked;
    }

    public void setSellingBlocked(Boolean sellingBlocked) {
        this.sellingBlocked = sellingBlocked;
    }
}
