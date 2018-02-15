package pro.lukasgorny.dto.user;

/**
 * Created by lukaszgo on 2018-02-15.
 */
public class MessageResultDto {

    private String title;
    private String content;
    private String senderEmail;
    private boolean isNew;

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

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
