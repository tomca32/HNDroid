package io.tomislav.hndroid;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;
import org.parceler.Transient;

import java.util.List;
import java.util.Map;

@Parcel(Parcel.Serialization.BEAN)
public class Item {
    private Long score;
    private String author;
    private Long id;
    private Boolean deleted;
    private Long timestamp;
    private String title;
    private String text;
    private String type;
    private Long descendants;
    private String url;
    private List<Long> children;

    @ParcelConstructor
    public Item(Long score, String author, Long id, Boolean deleted, Long timestamp, String title, String text, String type, Long descendants, String url, List<Long> children) {
        this.score = score;
        this.author = author;
        this.id = id;
        this.deleted = deleted;
        this.timestamp = timestamp;
        this.title = title;
        this.text = text;
        this.type = type;
        this.descendants = descendants;
        this.url = url;
        this.children = children;
    }

    public Item(Map<String, Object> itemMap) {
        score = (Long) itemMap.get("score");
        author = (String) itemMap.get("by");
        id = (Long) itemMap.get("id");
        deleted = (Boolean) itemMap.get("deleted");
        timestamp = (Long) itemMap.get("time");
        title = (String) itemMap.get("title");
        text = (String) itemMap.get("text");
        type = (String) itemMap.get("type");
        descendants = (Long) itemMap.get("descendants");
        url = (String) itemMap.get("url");
        children = (List<Long>) itemMap.get("kids");
    }

    public Long getScore() {
        return score;
    }

    public String getAuthor() {
        return author;
    }

    public Long getId() {
        return id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public Long getDescendants() {
        return descendants;
    }

    public String getUrl() {
        return url;
    }

    public List<Long> getChildren() {
        return children;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Transient
    public boolean isDeleted() {
        return deleted != null && deleted;
    }
}
