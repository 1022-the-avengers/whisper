package top.arron206.whisper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.arron206.whisper.dao.RecordRepository;
import top.arron206.whisper.dto.*;
import top.arron206.whisper.entity.User;
import top.arron206.whisper.service.*;
import top.arron206.whisper.vo.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/verification/message")
public class Message {

    @Autowired
    UserService userService;

    @Autowired
    UserMessage userMessage;

    @Autowired
    RecordRepository recordRepository;

    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public InfoMessage getIndex(){
        User user = userService.setUser();
        Map<Object, Object> unreadMessages = userMessage.getAllUnread(user.getId());
        Map<Object, Object> unreadNum = userMessage.getAllUnreadNum(user.getId());
        LinkMessage linkMessage = new LinkMessage(
                "index /verification/message/index",
                "/verification/message/index",
                "index",
                "application/json");
        return new InfoMessage("获取成功", unreadMessages, unreadNum, new UserInformation(
                user.getId(), user.getNickname(), user.getPic(), user.getGender(), user.getAge()
        ), linkMessage);
    }

    @RequestMapping(value = "/history", method = {RequestMethod.GET})
    public HistoryMessage getHistory(@RequestParam Integer customer, @RequestParam Integer page){
        User user = userService.setUser();
        userMessage.readMessage(user.getId(),customer);
        List<RecordDto> records = recordRepository.getChatRecord(user.getId(), customer, page);
        LinkMessage linkMessage = new LinkMessage(
                "index /verification/message/index",
                "/verification/message/index",
                "index",
                "application/json");
        return new HistoryMessage("获取成功", records, linkMessage);
    }
}
