package top.arron206.whisper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.arron206.whisper.dao.UserRepository;
import top.arron206.whisper.entity.User;
import top.arron206.whisper.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private User user;
    private User userInDataBase;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
        this.userInDataBase = userRepository.findByAccount(user.getAccount());
    }

    @Override
    public User getUserInDataBase() {
        return userInDataBase;
    }

    @Override
    public void setUserInDataBase(User userInDataBase) {
        this.userInDataBase = userInDataBase;
    }

    @Override
    public void encode(){
        this.user.md5Password();
    }

    @Override
    public boolean checkPassword(){
        return this.user.getPassword().equals(this.userInDataBase.getPassword());
    }

    @Override
    public void save() {
        userRepository.save(this.user);
    }

    @Override
    public boolean isRegistered() {
       return  userInDataBase!=null;
    }

    @Override
    public User findById(int id){
        return userRepository.findById(id);
    }


}
