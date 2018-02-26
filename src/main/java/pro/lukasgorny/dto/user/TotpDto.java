package pro.lukasgorny.dto.user;

/**
 * Created by lukaszgo on 2018-02-26.
 */
public class TotpDto {

    private String code;
    private String email;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
