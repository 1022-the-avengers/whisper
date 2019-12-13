package top.arron206.whisper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import top.arron206.whisper.dao.UserRepository;
import top.arron206.whisper.dto.UserInformation;
import top.arron206.whisper.entity.User;
import top.arron206.whisper.service.UserInformationService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInformationServiceImpl implements UserInformationService {
    @Autowired
    UserRepository userRepository;

    public void saveUserInformation(User user) {
        User realUser = this.userRepository.findById(user.getId());
        user.setAccount(realUser.getAccount());
        user.setPassword(realUser.getPassword());
        this.userRepository.save(user);
    }

    public UserInformation getUserInformation(int id) {
        User user = this.userRepository.findById(id);
        return new UserInformation(user.getId(), user.getNickname(),
                user.getPic(), user.getGender(), user.getAge());
    }

    public List<UserInformation> getUserInformations(String nickname, String gender, Integer minAge, Integer maxAge) {
        List<User> users = this.userRepository.getUsers(nickname, gender, minAge, maxAge);
        List<UserInformation> userInformations = new ArrayList<>();
        for (User user : users)
            userInformations.add(new UserInformation(user.getId(), user.getNickname(),
                    user.getPic(), user.getGender(), user.getAge()));
        return userInformations;
    }
}
