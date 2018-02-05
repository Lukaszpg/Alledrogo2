package pro.lukasgorny.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import pro.lukasgorny.dto.ResetPasswordDto;
import pro.lukasgorny.event.OnResetPassword;
import pro.lukasgorny.service.email.EmailSenderService;
import pro.lukasgorny.service.registration.RegistrationService;

/**
 * Created by lukaszgo on 2018-02-05.
 */
@Component
public class ResetPasswordListener implements ApplicationListener<OnResetPassword> {

    private final RegistrationService registrationService;
    private final MessageSource messages;
    private final EmailSenderService emailSenderService;

    @Autowired
    public ResetPasswordListener(RegistrationService registrationService, MessageSource messages, EmailSenderService emailSenderService) {
        this.registrationService = registrationService;
        this.messages = messages;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public void onApplicationEvent(OnResetPassword onResetPassword) {
        this.resetPassword(onResetPassword);
    }

    private void resetPassword(OnResetPassword event) {
        ResetPasswordDto resetPasswordDto = event.getResetPasswordDto();
        resetPasswordDto = registrationService.generateAndSaveUserNewPassword(resetPasswordDto);

        String recipientAddress = resetPasswordDto.getEmail();
        String subject = messages.getMessage("email.reset.password.success.title", null, resetPasswordDto.getLocale());
        String message = messages.getMessage("email.reset.password.success", null, resetPasswordDto.getLocale()) + " " + resetPasswordDto.getNewPassword();
        emailSenderService.sendEmail(recipientAddress, subject, message);
    }
}
