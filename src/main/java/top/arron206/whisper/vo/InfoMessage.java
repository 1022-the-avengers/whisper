package top.arron206.whisper.vo;

import top.arron206.whisper.dto.UserIndexDto;

import java.util.LinkedHashMap;
import java.util.Map;

public class InfoMessage {
    private String message;
    private Map<Object, Object> unreadMessages;
    private Map<Object, Object> unreadNum;
    private UserIndexDto userMessage;
    private Map<String, String> link = new LinkedHashMap<>();

    public InfoMessage(){

    }

    public InfoMessage(String message, Map<Object, Object> unreadMessages, Map<Object, Object> unreadNum, UserIndexDto userMessage, LinkMessage linkMessage) {
        this.message = message;
        this.unreadMessages = unreadMessages;
        this.unreadNum = unreadNum;
        this.userMessage = userMessage;
        this.link = linkMessage.getLink();
    }

    public UserIndexDto getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(UserIndexDto userMessage) {
        this.userMessage = userMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<Object, Object> getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(Map<Object, Object> unreadMessages) {
        this.unreadMessages = unreadMessages;
    }

    public Map<String, String> getLink() {
        return link;
    }

    public void setLink(Map<String, String> link) {
        this.link = link;
    }

    public Map<Object, Object> getUnreadNum() {
        return unreadNum;
    }

    public void setUnreadNum(Map<Object, Object> unreadNum) {
        this.unreadNum = unreadNum;
    }
}
