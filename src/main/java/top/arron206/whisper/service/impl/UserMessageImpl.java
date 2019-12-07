package top.arron206.whisper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.arron206.whisper.dao.RecordRepository;
import top.arron206.whisper.entity.Record;
import top.arron206.whisper.service.UserMessage;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserMessageImpl implements UserMessage {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private RecordRepository recordRepository;

    public void readMessage(Integer recipientId, Integer senderId) {
        redisTemplate.opsForHash().delete("unread"+recipientId, String.valueOf(senderId));
        redisTemplate.opsForHash().delete("unreadNum"+recipientId, String.valueOf(senderId));
    }

    public Object getUnread(Integer recipientId, Integer senderId) {
        return redisTemplate.opsForHash().get("unread"+recipientId, String.valueOf(senderId));
    }

    public Map<Object, Object> getAllUnread(Integer recipientId) {
        return redisTemplate.opsForHash().entries("unread"+recipientId);
    }

    public Map<Object, Object> getAllUnreadNum(Integer recipientId) {
        return redisTemplate.opsForHash().entries("unreadNum"+recipientId);
    }

    @Override
    public void save(Record record) {
        recordRepository.save(record);
    }

    public void addMessage(Integer recipientId, Integer senderId, String nickname, String content, String pic) {
        Map<String, Map<String, String>> unreadMessage = new HashMap<>();
        Map<String, String> message = new HashMap<>();
        message.put("nickname", nickname);
        message.put("content", content);
        message.put("pic", pic);
        unreadMessage.put(String.valueOf(senderId), message);
        String key = "unread"+recipientId;
        String numKey = "unreadNum"+recipientId;
        redisTemplate.opsForHash().putAll(key, unreadMessage);
        redisTemplate.opsForHash().increment(numKey, String.valueOf(senderId), 1);
    }
}
