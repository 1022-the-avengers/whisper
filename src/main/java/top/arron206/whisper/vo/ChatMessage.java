package top.arron206.whisper.vo;

import top.arron206.whisper.dto.RecordDto;

import java.util.HashMap;
import java.util.Map;

public class ChatMessage {
    private Integer senderId;
    private Integer recipientId;
    private Map<String, String> content;

    public ChatMessage(){

    }

    public ChatMessage(RecordDto recordDto, boolean my) {
        this.senderId = recordDto.getSenderId();
        this.recipientId = recordDto.getRecipientId();
        this.content = new HashMap<>();
        if(my){
            this.content.put("my", recordDto.getContent());
        }else{
            this.content.put("he", recordDto.getContent());
        }
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }
}
