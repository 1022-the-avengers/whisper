package top.arron206.whisper.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String content;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date time;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, optional=false)
    @JoinColumn(name="sender_id")
    private User sender;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL, optional=false)
    @JoinColumn(name="recipient_id")
    private User recipient;

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
