package io.tomislav.hndroid.adapters;

import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.tomislav.hndroid.Comment;
import io.tomislav.hndroid.R;

public class CommentsAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    private LongSparseArray<Comment> comments;

    public CommentsAdapter(LongSparseArray<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment comment = comments.valueAt(position);
        holder.setCommentContents(comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

}
