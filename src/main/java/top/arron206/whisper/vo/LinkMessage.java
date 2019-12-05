package top.arron206.whisper.vo;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkMessage {
    private Map<String, String> link = new LinkedHashMap<>();

    public LinkMessage(String rel, String href, String title, String type){
        link.put("rel", rel);
        link.put("href", href);
        link.put("title", title);
        link.put("type", type);
    }

    public Map<String, String> getLink() {
        return link;
    }

    public void setLink(Map<String, String> link) {
        this.link = link;
    }
}
