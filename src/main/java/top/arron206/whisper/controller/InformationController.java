package top.arron206.whisper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.arron206.whisper.dao.impl.UserSearch;
import top.arron206.whisper.dto.UserInformation;
import top.arron206.whisper.entity.Impression;
import top.arron206.whisper.entity.User;
import top.arron206.whisper.service.ImpressionService;
import top.arron206.whisper.service.UserInformationService;
import top.arron206.whisper.service.UserService;
import top.arron206.whisper.vo.GeneralResponse;
import top.arron206.whisper.vo.InformationMessage;

import java.util.List;

@RestController
@RequestMapping(value = "/verification/user")
public class InformationController {
    @Autowired
    UserInformationService userInformationService;
    @Autowired
    UserService userService;
    @Autowired
    ImpressionService impressionService;

    @RequestMapping(value = "/information", method = RequestMethod.POST)
    public GeneralResponse saveInformation(@RequestBody User user) {
        this.userInformationService.saveUserInformation(user);
        return new GeneralResponse("用户资料修改成功", null,
                "",
                "",
                "",
                "");
    }

    @RequestMapping(value = "/information", method = RequestMethod.GET)
    public GeneralResponse getInformation() {
        UserInformation userInformation =  userInformationService.getUserInformation();
        return new GeneralResponse("用户资料请求成功", userInformation,
                "",
                "",
                "",
                "");
    }

    @RequestMapping(value = "/informations", method = RequestMethod.GET)
    public GeneralResponse getInformations(@RequestParam(name = "nickname", required = false) String nickname,
                                          @RequestParam(name = "gender", required = false) String gender,
                                          @RequestParam(name = "minAge", required = false) int minAge,
                                          @RequestParam(name = "maxAge", required = false) int maxAge) {
        List<UserInformation> userInformations =  userInformationService.getUserInformations(nickname, gender, minAge, maxAge);
        return new GeneralResponse("用户资料请求成功", userInformations,
                "",
                "",
                "",
                "");
    }
}
