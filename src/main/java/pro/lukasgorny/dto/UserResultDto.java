package pro.lukasgorny.dto;

/**
 * Created by Łukasz on 15.12.2017.
 */
public class UserResultDto {
    private String email;
    private String registeredSince;
    private String lastLogin;
    private UserExtendedDto userExtendedDto;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisteredSince() {
        return registeredSince;
    }

    public void setRegisteredSince(String registeredSince) {
        this.registeredSince = registeredSince;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public UserExtendedDto getUserExtendedDto() {
        return userExtendedDto;
    }

    public void setUserExtendedDto(UserExtendedDto userExtendedDto) {
        this.userExtendedDto = userExtendedDto;
    }
}
