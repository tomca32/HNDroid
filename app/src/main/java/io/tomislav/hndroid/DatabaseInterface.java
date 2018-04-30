package io.tomislav.hndroid;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.androidannotations.annotations.EBean;

import java.util.List;

@EBean
public class DatabaseInterface {
    private static final String DATABASE_URL = "https://hacker-news.firebaseio.com";
    private static final String TOP_STORIES = "v0/topstories";
    private static final String ITEM = "v0/item/";

    private String itemUrl(Long itemId) {
        return ITEM + itemId;
    }

    private FirebaseDatabase database;

    private FirebaseDatabase getDatabaseInstance() {
        if (database == null) {
            database = FirebaseDatabase.getInstance(DATABASE_URL);
        }
        return database;
    }

    public DatabaseReference getTopStoriesRef() {
        return getDatabaseInstance().getReference(TOP_STORIES);
    }

    public DatabaseReference getItemRef(Long itemId) {
        return getDatabaseInstance().getReference(itemUrl(itemId));
    }
}
