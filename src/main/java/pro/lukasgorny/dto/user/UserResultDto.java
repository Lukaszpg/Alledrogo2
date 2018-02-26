package pro.lukasgorny.dto.user;

/**
 * Created by Łukasz on 15.12.2017.
 */
public class UserResultDto {
    private String id;
    private String email;
    private String registeredSince;
    private String lastLogin;
    private UserExtendedDto userExtendedDto;
    private Boolean using2fa;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getUsing2fa() {
        return using2fa;
    }

    public void setUsing2fa(Boolean using2fa) {
        this.using2fa = using2fa;
    }
}
