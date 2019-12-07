package top.arron206.whisper.service;

public interface UserOnline {
    boolean isOnline(Integer userId);
    long connect(Integer usersId);
    long disconnect(Integer values);
}
