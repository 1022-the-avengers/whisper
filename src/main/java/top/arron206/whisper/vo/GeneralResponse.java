package top.arron206.whisper.vo;

import java.util.LinkedHashMap;
import java.util.Map;

public class GeneralResponse {
    private String message;
    private Object data;
    private Map<String, String> link = new LinkedHashMap<>();

    public GeneralResponse(String message, Object data, String rel,
                    String href, String title, String type) {
        this.message = message;
        this.data = data;
        this.link.put("rel", rel);
        this.link.put("href", href);
        this.link.put("title", title);
        this.link.put("type", type);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getRel() {
        return link.get("rel");
    }

    public void setRel(String rel) {
        this.link.replace("rel", rel);
    }

    public String getHref() {
        return link.get("href");
    }

    public void setHref(String href) {
        this.link.replace("href", href);
    }

    public String getTitle() {
        return link.get("title");
    }

    public void setTitle(String title) {
        this.link.replace("title", title);
    }

    public String getType() {
        return link.get("type");
    }

    public void setType(String type) {
        this.link.replace("type", type);
    }
}
