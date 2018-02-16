package pro.lukasgorny.model;

import javax.persistence.*;

/**
 * Created by lukaszgo on 2018-02-15.
 */
@Entity
@Table(name = "messages")
public class Message extends Model {
    private String title;
    private String content;
    private User receiver;
    private User sender;
    private boolean isNew;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Lob
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @ManyToOne
    @JoinColumn(name = "sender_id")
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean aNew) {
        isNew = aNew;
    }
}
