package top.arron206.whisper.service;

public interface UserMessage {
    void readMessage(String key, Object item);
    Long getMessage(String key, String item, int by);
}
