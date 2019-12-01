package top.arron206.whisper.entity;

import javax.persistence.*;


@Entity
@Table(name = "impression")
public class Impression  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String content;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, optional=false)
    @JoinColumn(name="commenter_id")
    private User commenter;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, optional=false)
    @JoinColumn(name="target_id")
    private User target;

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

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }
}
