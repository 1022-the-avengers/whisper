package top.arron206.whisper.vo;

import top.arron206.whisper.dto.RecordDto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HistoryMessage {
    private String message;
    private List<ChatMessage> historyMessage;
    private Map<String, String> link = new LinkedHashMap<>();

    public HistoryMessage(String message, List<ChatMessage> historyMessage, LinkMessage linkMessage) {
        this.message = message;
        this.historyMessage = historyMessage;
        this.link = linkMessage.getLink();
    }

    public HistoryMessage(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ChatMessage> getHistoryMessage() {
        return historyMessage;
    }

    public void setHistoryMessage(List<ChatMessage> historyMessage) {
        this.historyMessage = historyMessage;
    }

    public Map<String, String> getLink() {
        return link;
    }

    public void setLink(Map<String, String> link) {
        this.link = link;
    }
}
