package pro.lukasgorny.dto.user;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by lukaszgo on 2018-02-15.
 */
public class MessageSaveDto {

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    @Size(max = 1000)
    private String content;

    private String receiverId;
    private String senderEmail;
    private String receiverName;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}
