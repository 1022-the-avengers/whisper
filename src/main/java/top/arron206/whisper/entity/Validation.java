package top.arron206.whisper.entity;

import javax.persistence.*;

@Entity
@Table(name = "validation")
public class Validation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="sender_id")
    private User sender;

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="receiver_id")
    private User receiver;

    private String content;

    //1处理中，2确认，3拒绝。
    private int status;

    public Validation() {}

    public Validation(User sender, User receiver, String content, int status) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
