package top.arron206.whisper.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import top.arron206.whisper.entity.Record;
import top.arron206.whisper.entity.User;
import top.arron206.whisper.service.RelationshipService;
import top.arron206.whisper.service.UserMessage;
import top.arron206.whisper.service.UserOnline;
import top.arron206.whisper.service.UserService;
import top.arron206.whisper.vo.ChatMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Socket {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMessage userMessage;

    @Autowired
    private UserOnline userOnline;

    @Autowired
    private RelationshipService relationshipService;

    @MessageMapping("/chat")
    public void sayHello(ChatMessage message){
        User sender = userService.findById(message.getSenderId());
        User recipient = userService.findById(message.getRecipientId());
        if(sender==null||recipient==null)
            return;
        String content = message.getContent();
        String destination = "/subscription/" + recipient.getId();
        String origin = "/subscription/" + sender.getId();
        if(relationshipService.isFriend(recipient, sender)){
            Record record = new Record(content, new Date(), sender, recipient);
            userMessage.save(record);
            if(userOnline.isOnline(recipient.getId()))
                messagingTemplate.convertAndSend(destination, new HashMap<String, String>(){{
                    put("content", content);
                }});
            else
                userMessage.addMessage(recipient.getId(), sender.getId(), sender.getNickname(), content, sender.getPic());
            messagingTemplate.convertAndSend(origin, new HashMap<String, String>(){{
                put("content","发送成功");
            }});
        }else{
            messagingTemplate.convertAndSend(origin, new HashMap<String, String>(){{
                put("content","对方不是你好友");
            }});
        }
    }

    @MessageMapping("/disconnection")
    public void disconnect(@Headers Map<String, Object> head) {
        LinkedMultiValueMap lmp = (LinkedMultiValueMap)head.get("nativeHeaders");
        if(lmp.get("userId")!=null && lmp.get("userId").size()!=0){
            userOnline.disconnect(Integer.valueOf((String)lmp.get("userId").get(0)));
        }
    }
}
