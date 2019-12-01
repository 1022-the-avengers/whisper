package top.arron206.whisper.service;

public interface UserOnline {
    boolean isOnline(String key, Integer userId);
    long connect(String key,Integer... usersId);
    long disconnect(String key, Integer... values);
}
