package top.arron206.whisper.entity;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String account;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column()
    private String pic;

    @Column(length = 2)
    private String gender;

    @Column()
    private Integer age;

    @OneToMany(targetEntity = Record.class, mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Record> senderRecords;

    @OneToMany(targetEntity = Record.class, mappedBy = "recipient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Record> recipientRecords;

    @OneToMany(targetEntity = Impression.class, mappedBy = "commenter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Impression> commenterImpression;

    @OneToMany(targetEntity = Impression.class, mappedBy = "target", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Impression> targetImpression;

    @OneToMany(targetEntity = Friendship.class, mappedBy = "host", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Friendship> hostFriendship;

    @OneToMany(targetEntity = Friendship.class, mappedBy = "custom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Friendship> customFriendship;

    public void md5Password() {
        SimpleHash encodedPassword = new SimpleHash("MD5", password, ByteSource.Util.bytes(account), 3);
        this.password = encodedPassword.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Record> getSenderRecords() {
        return senderRecords;
    }

    public void setSenderRecords(List<Record> senderRecords) {
        this.senderRecords = senderRecords;
    }

    public List<Record> getRecipientRecords() {
        return recipientRecords;
    }

    public void setRecipientRecords(List<Record> recipientRecords) {
        this.recipientRecords = recipientRecords;
    }

    public List<Impression> getCommenterImpression() {
        return commenterImpression;
    }

    public void setCommenterImpression(List<Impression> commenterImpression) {
        this.commenterImpression = commenterImpression;
    }

    public List<Impression> getTargetImpression() {
        return targetImpression;
    }

    public void setTargetImpression(List<Impression> targetImpression) {
        this.targetImpression = targetImpression;
    }

    public List<Friendship> getHostFriendship() {
        return hostFriendship;
    }

    public void setHostFriendship(List<Friendship> hostFriendship) {
        this.hostFriendship = hostFriendship;
    }

    public List<Friendship> getCustomFriendship() {
        return customFriendship;
    }

    public void setCustomFriendship(List<Friendship> customFriendship) {
        this.customFriendship = customFriendship;
    }
}
