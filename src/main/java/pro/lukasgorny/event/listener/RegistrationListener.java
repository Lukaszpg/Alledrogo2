package pro.lukasgorny.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import pro.lukasgorny.enums.Templates;
import pro.lukasgorny.event.OnRegistrationCompleteEvent;
import pro.lukasgorny.model.User;
import pro.lukasgorny.service.email.EmailSenderService;
import pro.lukasgorny.service.user.UserService;

import java.util.UUID;

/**
 * Created by Łukasz on 24.10.2017.
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final UserService service;
    private final MessageSource messages;
    private final EmailSenderService emailSenderService;

    @Autowired
    public RegistrationListener(UserService service, MessageSource messages, EmailSenderService emailSenderService) {
        this.service = service;
        this.messages = messages;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = messages.getMessage("email.registration.success.title", null, event.getLocale());
        String confirmationUrl = event.getAppUrl() + "/" + Templates.TOKEN_ACTIVATE + "?token=" + token;
        String message = messages.getMessage("email.registration.success", null, event.getLocale()) + " " + "http://localhost:8080" + confirmationUrl;

        emailSenderService.sendEmail(recipientAddress, subject, message);
    }
}
