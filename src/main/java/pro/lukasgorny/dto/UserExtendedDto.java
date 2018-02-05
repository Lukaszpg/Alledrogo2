package pro.lukasgorny.dto;

import pro.lukasgorny.enums.VoivodeshipEnum;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Łukasz on 04.02.2018.
 */
public class UserExtendedDto {

    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String surname;

    private String companyName;

    @NotBlank
    private String address;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String city;

    @NotNull
    private VoivodeshipEnum voivodeship;

    @NotBlank
    private String firstPhoneNumber;

    private String secondPhoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
