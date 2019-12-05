package top.arron206.whisper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.arron206.whisper.service.UserOnline;

@Component
public class UserOnlineImpl implements UserOnline {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    public boolean isOnline(String key, Integer userId) {
        try {
            Boolean res = redisTemplate.opsForSet().isMember(key, userId);
            if(res==null){
                return false;
            }else{
                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public long connect(String key,Integer... usersId) {
        try {
            Long num = redisTemplate.opsForSet().add(key, usersId);
            if(num==null){
                return 0;
            }else{
                return num;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    public long disconnect(String key, Integer... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
