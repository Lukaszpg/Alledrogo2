package pro.lukasgorny.event;

import org.springframework.context.ApplicationEvent;

import pro.lukasgorny.dto.user.ResetPasswordDto;

/**
 * Created by lukaszgo on 2018-02-05.
 */
public class OnResetPassword extends ApplicationEvent {

    private ResetPasswordDto resetPasswordDto;

    public OnResetPassword(ResetPasswordDto resetPasswordDto) {
        super(resetPasswordDto);

        this.resetPasswordDto = resetPasswordDto;
    }

    public ResetPasswordDto getResetPasswordDto() {
        return resetPasswordDto;
    }

    public void setResetPasswordDto(ResetPasswordDto resetPasswordDto) {
        this.resetPasswordDto = resetPasswordDto;
    }
}
