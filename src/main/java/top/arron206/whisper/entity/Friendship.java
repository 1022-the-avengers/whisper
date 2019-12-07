package top.arron206.whisper.entity;


import javax.persistence.*;

@Entity
@Table(name = "friendship")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20)
    private String groupName;

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="host_id")
    private User host;

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="custom_id")
    private User custom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public User getCustom() {
        return custom;
    }

    public void setCustom(User custom) {
        this.custom = custom;
    }
}
