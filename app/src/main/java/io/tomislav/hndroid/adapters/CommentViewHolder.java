package io.tomislav.hndroid.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import io.tomislav.hndroid.Comment;
import io.tomislav.hndroid.R;

class CommentViewHolder extends RecyclerView.ViewHolder {
    CommentViewHolder(View itemView) {
        super(itemView);
    }

    void setCommentContents(Comment comment) {
        if (comment.getText() == null) {
            Log.d("COMMENT: ", comment.toString());
        }
        ((TextView) itemView.findViewById(R.id.comment_text)).setText(Html.fromHtml(comment.getText()));
    }
}
