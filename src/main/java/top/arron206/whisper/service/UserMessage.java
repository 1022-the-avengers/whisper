package top.arron206.whisper.service;

import top.arron206.whisper.entity.Record;

import java.util.Map;

public interface UserMessage {
    void readMessage(Integer recipientId, Integer senderId);
    void addMessage(Integer recipientId, Integer senderId, String nickname, String content, String pic);
    Object getUnread(Integer recipientId, Integer senderId);
    Map<Object, Object> getAllUnread(Integer recipientId);
    Map<Object, Object> getAllUnreadNum(Integer recipientId);
    void save(Record record);
}
