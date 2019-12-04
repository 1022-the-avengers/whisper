package top.arron206.whisper.service;

public interface UserMessage {
    void getMessage(String key, Object item);
    Long addMessage(String key, String item, int by);
}
