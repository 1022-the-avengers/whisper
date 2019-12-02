package top.arron206.whisper.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.arron206.whisper.dao.UserRepository;
import top.arron206.whisper.entity.User;
import top.arron206.whisper.pojo.HttpException;
import top.arron206.whisper.pojo.TokenManager;
import top.arron206.whisper.service.UserService;
import top.arron206.whisper.vo.AuthMessage;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class Auth {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public AuthMessage login(@RequestBody User user, HttpServletResponse response) throws HttpException {
        userService.setUser(user);
        userService.encode();
        if(!userService.isRegistered()){
            throw new HttpException(HttpStatus.UNAUTHORIZED, AuthMessage.getAuthMessage(
                    "账号未注册","", "registration /auth/registration",
                    "/auth/registration", "registration", "application/json"));
        } else if(!userService.checkPassword()){
            throw new HttpException(HttpStatus.UNAUTHORIZED, AuthMessage.getAuthMessage(
                    "账号密码不匹配","", "login /auth/login",
                    "/auth/login", "login", "application/json"));
        }else{
            response.setStatus(200);
            return AuthMessage.getAuthMessage(
                    "登录成功",TokenManager.sign(user.getId()), "messages /management/messages",
                    "/management/messages", "messages", "application/json");
        }
    }

    @RequestMapping(value = "/registration", method = {RequestMethod.POST})
    public AuthMessage register(@RequestBody User user, HttpServletResponse response) throws HttpException {
        userService.setUser(user);
        if(userService.isRegistered()){
            throw new HttpException(HttpStatus.FORBIDDEN, AuthMessage.getAuthMessage(
                    "账号已注册","", "registration /auth/registration",
                    "/auth/registration", "registration", "application/json"));
        }else if(user.getPassword().length()<8){
            throw new HttpException(HttpStatus.FORBIDDEN, AuthMessage.getAuthMessage(
                    "密码过短","", "registration /auth/registration",
                    "/auth/registration", "registration", "application/json"));
        }else{
            userService.encode();
            userService.save();
            response.setStatus(201);
            return AuthMessage.getAuthMessage(
                    "注册成功", TokenManager.sign(userService.getUser().getId()), "messages /management/messages",
                    "/management/messages", "messages", "application/json");
        }
    }
}
