package io.tomislav.hndroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.LongSparseArray;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import java.util.List;
import java.util.Map;

@EActivity
public class MainActivity extends AppCompatActivity {

    @Bean
    DatabaseInterface db;
    private List<Long> topStories;
    private LongSparseArray<Story> stories = new LongSparseArray<>();
    private StoriesAdapter adapter;
    private RecyclerView recyclerView;

    private void listenToTopStories() {
        db.getTopStoriesRef().limitToFirst(50).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                topStories = (List<Long>) dataSnapshot.getValue();
                for (final Long id : topStories) {
                    db.getItemRef(id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            stories.append(id, new Story((Map<String, Object>) dataSnapshot.getValue()));
                            updateAdapter();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                initializeAdapter();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listenToTopStories();
    }

    private void initializeAdapter() {
        if (adapter == null) {
            adapter = new StoriesAdapter(topStories, stories);
            recyclerView = findViewById(R.id.story_recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
            recyclerView.setAdapter(adapter);
        }
    }

    private void updateAdapter() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
