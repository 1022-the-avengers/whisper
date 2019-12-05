package top.arron206.whisper.service;

import java.util.List;
import java.util.Map;

public interface UserMessage {
    void readMessage(String key, String item);
    void addMessage(String key, Map<String, Map<String, String>> message);
    Object getUnread(String key, String item);
    Map<Object, Object> getAllUnread(String key);
}
