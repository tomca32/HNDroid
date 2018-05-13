package io.tomislav.hndroid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.List;

import static io.tomislav.hndroid.CommentsActivity.STORY;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoryViewHolder> {
    private LongSparseArray<Item> stories;
    private List<Long> storyIds;

    StoriesAdapter(List<Long> storyIds, LongSparseArray<Item> stories) {
        this.storyIds = storyIds;
        this.stories = stories;
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_item, parent, false);
        return new StoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        Item item = stories.get(storyIds.get(position));
        holder.setStoryContents(item);
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

        private void setCommentCount(Long commentCount) {
            ((TextView) itemView.findViewById(R.id.story_comment_count)).setText(String.valueOf(commentCount));
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
                Long descendants = item.getDescendants();
                if (descendants != null) {
                    setCommentCount(descendants);
                }
                itemView.setOnClickListener(v -> {
                    if (item.getUrl() == null) {
                        openComments(v, item);
                    } else {
                        openStoryInBrowser(v, item);
                    }
                });
                itemView.findViewById(R.id.story_comment_count).setOnClickListener(v -> openComments(v, item));
            }
        }
    }
}
