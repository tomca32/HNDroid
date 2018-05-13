package io.tomislav.hndroid.adapters;

import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.tomislav.hndroid.Comment;
import io.tomislav.hndroid.Item;
import io.tomislav.hndroid.R;

public class CommentsAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    private LongSparseArray<Comment> comments;
    private Item story;

    public CommentsAdapter(Item story, LongSparseArray<Comment> comments) {
        this.comments = comments;
        this.story = story;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment comment = comments.get(story.getChildren().get(position));
        if (comment == null) {
            // skipping deleted comment
            onBindViewHolder(holder, position + 1);
        } else {
            holder.setCommentContents(comment);
        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
