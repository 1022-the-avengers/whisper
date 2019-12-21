package top.arron206.whisper.dao.impl;

public interface CountValidationMessage {
    int countUnprocessedOrUnreadMessage(int userId);

    void readMessage(int userId);
}
