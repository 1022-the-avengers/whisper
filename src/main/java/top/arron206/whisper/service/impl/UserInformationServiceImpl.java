package top.arron206.whisper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import top.arron206.whisper.dao.UserRepository;
import top.arron206.whisper.dto.UserInformation;
import top.arron206.whisper.entity.Friendship;
import top.arron206.whisper.entity.Impression;
import top.arron206.whisper.entity.User;
import top.arron206.whisper.service.ImpressionService;
import top.arron206.whisper.service.UserInformationService;
import top.arron206.whisper.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInformationServiceImpl implements UserInformationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    ImpressionService impressionService;

    public void saveUserInformation(User user) {
        userService.setUser();
        User realUser = userService.getUser();
        user.setId(realUser.getId());
        user.setAccount(realUser.getAccount());
        user.setPassword(realUser.getPassword());
        this.userRepository.save(user);
    }

    public UserInformation getUserInformation() {
        userService.setUser();
        User user = userService.getUser();
        List<String> impressions = impressionService.getImpressions(user.getId());
        return new UserInformation(user, impressions);
    }

    public List<UserInformation> getUserInformations(String nickname, String gender, Integer minAge, Integer maxAge) {
        List<User> users = this.userRepository.getUsers(nickname, gender, minAge, maxAge);
        List<UserInformation> userInformations = new ArrayList<>();
        for (User user : users) {
            List<String> impressions = impressionService.getImpressions(user.getId());
            userInformations.add(new UserInformation(user, impressions));
        }
        return userInformations;
    }
}
