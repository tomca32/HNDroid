package io.tomislav.hndroid.adapters;

import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.tomislav.hndroid.Item;
import io.tomislav.hndroid.R;

public class StoriesAdapter extends RecyclerView.Adapter<StoryViewHolder> {
    private LongSparseArray<Item> stories;
    private List<Long> storyOrder;

    public StoriesAdapter(List<Long> storyOrder, LongSparseArray<Item> stories) {
        this.stories = stories;
        this.storyOrder = storyOrder;
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_item, parent, false);
        return new StoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        Item item = stories.get(storyOrder.get(position));
        holder.setStoryContents(item);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }
}
