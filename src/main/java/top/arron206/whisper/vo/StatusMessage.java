package top.arron206.whisper.vo;

import java.util.LinkedHashMap;
import java.util.Map;

public class StatusMessage {
    private String message;
    private Map<String, String> link = new LinkedHashMap<>();

    public StatusMessage(){

    }

    public StatusMessage(String message,  LinkMessage linkMessage) {
        this.message = message;
        this.link = linkMessage.getLink();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getLink() {
        return link;
    }

    public void setLink(Map<String, String> link) {
        this.link = link;
    }
}
