package io.tomislav.hndroid;

import java.util.List;
import java.util.Map;

public class Comment extends Item {
    private List<Comment> childComments;

    public Comment(Map<String, Object> itemMap) {
        super(itemMap);
    }

    public List<Comment> getChildComments() {
        return childComments;
    }

    public void setChildComments(List<Comment> childComments) {
        this.childComments = childComments;
    }
}
