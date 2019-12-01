package top.arron206.whisper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.arron206.whisper.service.UserMessage;

@Component
public class UserMessageImpl implements UserMessage {
    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    public void readMessage(String key, Object item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    public Long getMessage(String key, String item, int by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }
}
