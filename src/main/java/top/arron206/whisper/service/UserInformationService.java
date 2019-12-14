package top.arron206.whisper.service;

import top.arron206.whisper.dto.UserInformation;
import top.arron206.whisper.entity.User;

import java.util.List;

public interface UserInformationService {
    void saveUserInformation(User user);

    UserInformation getUserInformation();

    List<UserInformation> getUserInformations(String nickname, String gender, Integer minAge, Integer maxAge);
}