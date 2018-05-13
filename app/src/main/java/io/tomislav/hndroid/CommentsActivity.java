package io.tomislav.hndroid;

import android.support.v4.util.LongSparseArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.Map;

import io.tomislav.hndroid.adapters.CommentsAdapter;

@EActivity
public class CommentsActivity extends AppCompatActivity {

    public static final String STORY = "STORY";

    private Item story;
    private LongSparseArray<Comment> comments = new LongSparseArray<>();
    private CommentsAdapter adapter;
    private RecyclerView recyclerView;

    @Bean
    DatabaseInterface db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        story = Parcels.unwrap(getIntent().getParcelableExtra(STORY));

        if (story.getText() != null) {
            displayStoryText();
        }

        if (story.getChildren() != null) {
            fetchComments();
        }
    }

    private void fetchComments() {
        for (Long commentId : story.getChildren()) {
            db.getItemRef(commentId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Comment newComment = new Comment((Map<String, Object>) dataSnapshot.getValue());
                    if (newComment.isDeleted()) {
                        return;
                    }
                    comments.append(newComment.getId(), newComment);
                    updateAdapter();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private void displayStoryText() {
        ((TextView) findViewById(R.id.story_text)).setText(Html.fromHtml(story.getText()));
    }

    private void updateAdapter() {
        if (adapter == null) {
            initializeAdapter();
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private void initializeAdapter() {
        adapter = new CommentsAdapter(story, comments);
        recyclerView = findViewById(R.id.comments_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
    }
}
