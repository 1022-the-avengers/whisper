package top.arron206.whisper.dto;

public class UserIndexDto {
    private int id;

    private String nickname;

    private String pic;

    private String gender;

    private Integer age;

    public UserIndexDto(int id, String nickname, String pic, String gender, Integer age) {
        this.id = id;
        this.nickname = nickname;
        this.pic = pic;
        this.gender = gender;
        this.age = age;
    }

    public UserIndexDto(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
