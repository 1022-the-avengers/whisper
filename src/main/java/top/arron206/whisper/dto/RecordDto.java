package top.arron206.whisper.dto;

import top.arron206.whisper.entity.User;

import java.util.Date;

public class RecordDto {
    private String content;

    private Date time;

    private Integer senderId;

    private Integer recipientId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(int sender) {
        this.senderId = sender;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipient) {
        this.recipientId = recipient;
    }
}
