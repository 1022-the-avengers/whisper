package top.arron206.whisper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.arron206.whisper.service.UserMessage;
import java.util.Map;

@Component
public class UserMessageImpl implements UserMessage {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public void readMessage(String key, String item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    public Object getUnread(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    public void addMessage(String key, Map<String, Map<String, String>> message) {
        redisTemplate.opsForHash().putAll(key, message);
    }

    public Map<Object, Object> getAllUnread(String key) {
        return redisTemplate.opsForHash().entries(key);
    }
}
