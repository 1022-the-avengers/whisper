package top.arron206.whisper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.arron206.whisper.service.UserOnline;

@Service
public class UserOnlineImpl implements UserOnline {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    public boolean isOnline(Integer userId) {
        try {
            Boolean res = redisTemplate.opsForSet().isMember("online", userId);
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


    public long connect(Integer usersId) {
        try {
            Long num = redisTemplate.opsForSet().add("online", usersId);
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


    public long disconnect(Integer values) {
        try {
            Long count = redisTemplate.opsForSet().remove("online", values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
