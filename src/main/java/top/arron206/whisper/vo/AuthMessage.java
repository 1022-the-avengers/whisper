package top.arron206.whisper.vo;


import java.util.LinkedHashMap;
import java.util.Map;

public class AuthMessage {
    private String message;
    private String token;
    private Map<String, String> link = new LinkedHashMap<>();

    public static AuthMessage getAuthMessage(String... args){
        AuthMessage authMessage = new AuthMessage();
        authMessage.setMessage(args[0]);
        authMessage.setToken(args[1]);
        authMessage.getLink().put("rel", args[2]);
        authMessage.getLink().put("href", args[3]);
        authMessage.getLink().put("title", args[4]);
        authMessage.getLink().put("type", args[5]);
        return authMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, String> getLink() {
        return link;
    }

    public void setLink(Map<String, String> link) {
        this.link = link;
    }
}
