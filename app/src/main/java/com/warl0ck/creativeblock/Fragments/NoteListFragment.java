package com.warl0ck.creativeblock.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.warl0ck.creativeblock.Activities.NoteActivity;
import com.warl0ck.creativeblock.DataBase.DatabaseHelper;
import com.warl0ck.creativeblock.Model.Note;
import com.warl0ck.creativeblock.Model.NoteRecyclerAdapter;
import com.warl0ck.creativeblock.R;

import java.util.ArrayList;

/**
 * Created by warl0ck on 12/16/2018.
 */



public class NoteListFragment extends Fragment {
    ArrayList<Note> mNoteArrayList;
    int result;
    public static final String TAG = "NoteListActivity";
    private RecyclerView.LayoutManager layoutManager;
    @Nullable
    @Override


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_note_list, container,false);

        NoteRecyclerAdapter adapter = new NoteRecyclerAdapter(getNoteArrayList());

        Log.d(TAG, "THE VALUE OF THE NoteListActivity getNoteArrayList is "
                + getNoteArrayList().isEmpty()
                + "\n the is extra info "
                + getNoteArrayList().size());

        final EditText etId = view.findViewById(R.id.etId);
        ImageButton add =  (ImageButton) view.findViewById(R.id.addNote);
        Button delete =  view.findViewById(R.id.deleteNote);

        RecyclerView mRecyclerView =  view.findViewById(R.id.note_recycler_Lview);

        layoutManager = new LinearLayoutManager(getActivity());


        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NoteActivity.class);
                startActivity(i);

            }
        });
    return view;
    }

    public ArrayList<Note> getNoteArrayList(){

        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        mNoteArrayList = dbHelper.getAllData();
        Log.d(TAG, "THE NoteListActivity getNoteArrayList method returned "+ mNoteArrayList.size());
        Log.d(TAG, "THE NoteListActivity getNoteArrayList isEmpty: "+ mNoteArrayList.isEmpty());
        return mNoteArrayList;
    }
}
