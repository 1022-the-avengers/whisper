package top.arron206.whisper.vo;

import java.util.List;

public class ImpressionMessage {
    private List<String> contents;
    private int targetId;

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }
}
