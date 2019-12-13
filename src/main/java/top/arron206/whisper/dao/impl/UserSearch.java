package top.arron206.whisper.dao.impl;

import top.arron206.whisper.entity.User;

import java.util.List;

public interface UserSearch {
    List<User> getUsers(String nickname, String gender, Integer minAge, Integer maxAge);
}
