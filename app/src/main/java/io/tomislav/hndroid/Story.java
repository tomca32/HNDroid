package io.tomislav.hndroid;

import java.util.List;
import java.util.Map;

public class Story {
    private Long score;
    private String author;
    private Long id;
    private Long timestamp;
    private String title;
    private String type;
    private Long descendants;
    private String url;
    private List<Long> children;

    public Story(Map<String, Object> storyMap) {
        score = (Long) storyMap.get("score");
        author = (String) storyMap.get("by");
        id = (Long) storyMap.get("id");
        timestamp = (Long) storyMap.get("time");
        title = (String) storyMap.get("title");
        type = (String) storyMap.get("type");
        descendants = (Long) storyMap.get("descendants");
        url = (String) storyMap.get("url");
        children = (List<Long>) storyMap.get("kids");
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
}
