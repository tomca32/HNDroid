package io.tomislav.hndroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.parceler.Parcels;

import io.tomislav.hndroid.CommentsActivity_;
import io.tomislav.hndroid.Item;
import io.tomislav.hndroid.R;

import static io.tomislav.hndroid.CommentsActivity.STORY;

class StoryViewHolder extends RecyclerView.ViewHolder {

    StoryViewHolder(View itemView) {
        super(itemView);
    }

    private void removeProgressBar() {
        itemView.findViewById(R.id.story_progress_bar).setVisibility(View.GONE);
    }

    private void setTitle(String title) {
        ((TextView) itemView.findViewById(R.id.story_title)).setText(title);
    }

    private void setScore(Long score) {
        ((TextView) itemView.findViewById(R.id.story_score)).setText(score.toString());
    }

    private void setUrl(String url) {
        TextView storyUrlTextView = itemView.findViewById(R.id.story_url);
        storyUrlTextView.setText(url == null ? "" : url);
    }

    private void setCommentCount(Item item) {
        Long descendants = item.getDescendants();
        TextView commentCount = itemView.findViewById(R.id.story_comment_count);
        View commentContainer = itemView.findViewById(R.id.story_comment_container);
        if (descendants != null) {
            commentCount.setText(String.valueOf(descendants));
            commentContainer.setOnClickListener(v -> openComments(v, item));
        } else {
            commentCount.setText("");
            commentContainer.setOnClickListener(null);
        }
    }

    private void setStoryLoadingState() {
        setTitle(itemView.getContext().getString(R.string.loading));
    }

    private void openStoryInBrowser(View v, Item item) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
        v.getContext().startActivity(browserIntent);
    }

    private void openComments(View v, Item item) {
        Context context = v.getContext();
        Intent commentsIntent = new Intent(context, CommentsActivity_.class);
        commentsIntent.putExtra(STORY, Parcels.wrap(item));
        context.startActivity(commentsIntent);
    }

    void setStoryContents(Item item) {
        if (item == null) {
            setStoryLoadingState();
        } else {
            removeProgressBar();
            setTitle(item.getTitle());
            setScore(item.getScore());
            setUrl(item.getUrl());
            setCommentCount(item);
            itemView.setOnClickListener(v -> {
                if (item.getUrl() == null) {
                    openComments(v, item);
                } else {
                    openStoryInBrowser(v, item);
                }
            });
        }
    }
}
