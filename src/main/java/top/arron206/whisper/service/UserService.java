package top.arron206.whisper.service;

import top.arron206.whisper.entity.User;
import top.arron206.whisper.pojo.HttpException;

public interface UserService {
    boolean checkPassword();
    void save();
    void encode();
    boolean isRegistered();
    User getUser();
    void setUser(User user);
}
