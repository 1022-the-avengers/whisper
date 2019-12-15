package top.arron206.whisper.vo;

import top.arron206.whisper.entity.User;

public class ValidationMessage {
    private int id;
    private String account;
    private String nickname;
    private String pic;
    private String content;
    private int status;

    public ValidationMessage() {}

    public ValidationMessage(int id, User user, String content, int status) {
        this.id = id;
        this.account = user.getAccount();
        this.nickname = user.getNickname();
        this.pic = user.getPic();
        this.content = content;
        this.status = status;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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
