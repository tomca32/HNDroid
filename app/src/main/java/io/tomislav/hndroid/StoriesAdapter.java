package io.tomislav.hndroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoryViewHolder> {
    private LongSparseArray<Story> stories;
    private List<Long> storyIds;

    StoriesAdapter(List<Long> storyIds, LongSparseArray<Story> stories) {
        this.storyIds = storyIds;
        this.stories = stories;
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.story_item, parent, false);
        return new StoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        Story story = stories.get(storyIds.get(position));
        holder.setStoryContents(story);
    }

    @Override
    public int getItemCount() {
        return storyIds.size();
    }

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

        private void setCommentCount(int commentCount) {
            ((TextView) itemView.findViewById(R.id.story_comment_count)).setText(String.valueOf(commentCount));
        }

        private void setStoryLoadingState() {
            setTitle(itemView.getContext().getString(R.string.loading));
        }

        void setStoryContents(Story story) {
            if (story == null) {
                setStoryLoadingState();
            } else {
                removeProgressBar();
                setTitle(story.getTitle());
                setScore(story.getScore());
                setUrl(story.getUrl());
                List<Long> children = story.getChildren();
                if (children != null) {
                    setCommentCount(children.size());
                }
                itemView.setOnClickListener(v -> {
                    if (story.getUrl() == null) return;

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(story.getUrl()));
                    v.getContext().startActivity(browserIntent);
                });
            }
        }
    }
}
