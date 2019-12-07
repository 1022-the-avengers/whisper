package top.arron206.whisper.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String content;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date time;

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="sender_id")
    private User sender;

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="recipient_id")
    private User recipient;

    public Record(){

    }

    public Record(String content, Date time, User sender, User recipient) {
        this.content = content;
        this.time = time;
        this.sender = sender;
        this.recipient = recipient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}
