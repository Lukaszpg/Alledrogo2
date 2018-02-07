package pro.lukasgorny.dto;

/**
 * Created by lukaszgo on 2018-02-07.
 */
public class ObserveResponseDto {
    private String message;
    private Boolean isSuccess;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }
}
