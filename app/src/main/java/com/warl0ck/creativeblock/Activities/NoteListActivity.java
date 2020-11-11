package com.warl0ck.creativeblock.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.warl0ck.creativeblock.Model.Note;
import com.warl0ck.creativeblock.Fragments.NoteListFragment;
import com.warl0ck.creativeblock.R;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Note> mNoteArrayList;
    int result;
    public static final String TAG = "NoteListActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        NoteListFragment nlf = new NoteListFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.mContainer) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            NoteListFragment firstFragment = new NoteListFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction()
                    .replace(R.id.mContainer, firstFragment);
            ft.addToBackStack("mNothing");

        } else {
            NoteListFragment myFragment2 = new NoteListFragment();
            //fm = getSupportFragmentManager();
            ft = ft.replace(R.id.mContainer, myFragment2);
            ft.addToBackStack("mNothingh");

        }
        ft.commit();
    }

}
