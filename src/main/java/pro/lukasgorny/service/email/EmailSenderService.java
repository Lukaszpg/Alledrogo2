package pro.lukasgorny.service.email;

/**
 * Created by Łukasz on 24.10.2017.
 */
public interface EmailSenderService {
    void sendEmail(String to, String title, String content);
}
