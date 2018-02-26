package pro.lukasgorny.model;

import pro.lukasgorny.enums.VoivodeshipEnum;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import org.jboss.aerogear.security.otp.api.Base32;

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
    private String name;
    private String surname;
    private String companyName;
    private String address;
    private String zipCode;
    private String city;
    private VoivodeshipEnum voivodeship;
    private String firstPhoneNumber;
    private String secondPhoneNumber;
    private Boolean isUsing2FA;
    private String secret;

    public User() {
        super();
        this.secret = Base32.random();
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Enumerated(EnumType.STRING)
    public VoivodeshipEnum getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(VoivodeshipEnum voivodeship) {
        this.voivodeship = voivodeship;
    }

    public String getFirstPhoneNumber() {
        return firstPhoneNumber;
    }

    public void setFirstPhoneNumber(String firstPhoneNumber) {
        this.firstPhoneNumber = firstPhoneNumber;
    }

    public String getSecondPhoneNumber() {
        return secondPhoneNumber;
    }

    public void setSecondPhoneNumber(String secondPhoneNumber) {
        this.secondPhoneNumber = secondPhoneNumber;
    }

    public Boolean getUsing2FA() {
        return isUsing2FA;
    }

    public void setUsing2FA(Boolean using2FA) {
        isUsing2FA = using2FA;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
