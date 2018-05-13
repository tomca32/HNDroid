package io.tomislav.hndroid.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import io.tomislav.hndroid.Comment;
import io.tomislav.hndroid.R;

class CommentViewHolder extends RecyclerView.ViewHolder {
    CommentViewHolder(View itemView) {
        super(itemView);
    }

    void setCommentContents(Comment comment) {
        ((TextView) itemView.findViewById(R.id.comment_text)).setText(comment.getText());
    }
}
