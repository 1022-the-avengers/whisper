package top.arron206.whisper.service;

import top.arron206.whisper.dto.UserInformation;
import top.arron206.whisper.entity.User;

public interface UserInformationService {
    void saveUserInformation(User user);

    UserInformation getUserInformation(int id);
}