package io.tomislav.hndroid;

import android.support.v7.widget.RecyclerView;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        StoryViewHolder vh = new StoryViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        Story story = stories.get(storyIds.get(position));
        String title;
        if (story != null) {
            title = story.getTitle();
        } else {
            title = "Loading...";
        }

        holder.setTitle(title);
    }

    @Override
    public int getItemCount() {
        return storyIds.size();
    }

    class StoryViewHolder extends RecyclerView.ViewHolder {

        StoryViewHolder(View itemView) {
            super(itemView);
        }

        public void setTitle(String title) {
            ((TextView) itemView.findViewById(R.id.story_title)).setText(title);
        }
    }
}
