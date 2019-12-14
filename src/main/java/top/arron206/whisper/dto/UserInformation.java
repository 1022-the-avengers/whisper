package top.arron206.whisper.dto;

import top.arron206.whisper.entity.User;

import java.util.List;

public class UserInformation {
    private int id;

    private String account;

    private String nickname;

    private String pic;

    private String gender;

    private Integer age;

    private List<String> impressions;

    public UserInformation(User user, List<String> impressions) {
        this.id = user.getId();
        this.account = user.getAccount();
        this.nickname = user.getNickname();
        this.pic = user.getPic();
        this.gender = user.getGender();
        this.age = user.getAge();
        this.impressions = impressions;
    }

    public UserInformation(int id, String nickname, String pic, String gender, Integer age) {
        this.id = id;
        this.nickname = nickname;
        this.pic = pic;
        this.gender = gender;
        this.age = age;
    }

    public UserInformation(){

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

    public List<String> getImpressions() {
        return impressions;
    }

    public void setImpressions(List<String> impressions) {
        this.impressions = impressions;
    }
}
