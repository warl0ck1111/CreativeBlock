package com.warl0ck.creativeblock.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.warl0ck.creativeblock.Activities.NoteActivity;
import com.warl0ck.creativeblock.Activities.NoteListActivity;
import com.warl0ck.creativeblock.DataBase.DatabaseHelper;
import com.warl0ck.creativeblock.Model.Note;
import com.warl0ck.creativeblock.R;

/**
 * Created by warl0ck on 12/13/2018.
 */

public class NoteActivityFragment extends Fragment {
    boolean hasSaved;
    EditText title, content;
    String Oid;

    /**
     * todo before selecting either save or update check whether
     *  the text was just viewed and nothing changed (was added or removed) before choosing the appropriate action
     *  and set the appropriate method for it
     *  sorry right now i can't think straight :-#
     *  ****also noticed when it is updating it is creating new object of note and resaving as a new note handle that as well
     */

    @Nullable
    @Override


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_note, container, false);

        NoteActivity na = new NoteActivity();
        final String TITLE = getActivity().getIntent().getStringExtra("TITLE");
        final String CONTENT = getActivity().getIntent().getStringExtra("CONTENT");
        final String ID = getActivity().getIntent().getStringExtra("ID");
        Oid = ID;
        final String TAG = "NoteActivity";

        hasSaved = false;


        final ImageButton btnSave = (ImageButton) view.findViewById(R.id.btnSave);

        title = (EditText) view.findViewById(R.id.title);
        content = (EditText) view.findViewById(R.id.note);

        title.setText(TITLE);
        content.setText(CONTENT);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Title = title.getText().toString();
                final String Content = content.getText().toString();

//check if both EditText are empty before saving
                if (TITLE == null && CONTENT == null) {
                    if (TextUtils.isEmpty(Title)) {
                        title.setError("Title is required");
                    }

                /*
                if (TextUtils.isEmpty(Content)){
                    title.setError("Title is required");
                   */

                    final Note note = new Note(Title, Content, System.currentTimeMillis(), System.currentTimeMillis());
                    Log.d(TAG, "THE NoteActivity value for title is " + Title + "THE NoteActivity value for title is " + Content);

                    DatabaseHelper db = new DatabaseHelper(getContext());

                    //Add note to database
                    long result = db.addNote(note);

                    //if -1 is returned then Save to SqLite db was not Successfull
                    if (result == -1) {
                        Toast.makeText(getContext(), "Save was not Successfull", Toast.LENGTH_LONG).show();
                    } else {
                        hasSaved = true;
                        Toast.makeText(getContext(), "Save was Successfull", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getActivity(), NoteListActivity.class);

                        startActivity(i);
                    }

                } else {
                    final Note note = new Note(Title, Content, System.currentTimeMillis(), System.currentTimeMillis());
                    Log.d(TAG, "THE NoteActivity value for title is " + Title + "THE NoteActivity value for title is " + Content);

                    DatabaseHelper db = new DatabaseHelper(getContext());

                    String[] id = {String.valueOf(ID)};
                    Log.d("intentExtraID", String.valueOf(ID));
                    long result = db.update(note, id);
                    if (result < 1) {
                        Toast.makeText(getContext(), "Save was not Successfull", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "Save was Successfull", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getActivity(), NoteListActivity.class);

                        startActivity(i);

                    }
                }
            }
        });


        return view;
    }


    @Override
    public void onStop() {
        super.onStop();
        final String Title = title.getText().toString();
        final String Content = content.getText().toString();


        if (hasSaved == false) {

            long result = -8; //make sure the number tag  doesnt interfarre with the Code
            final Note note = new Note(Title, Content, System.currentTimeMillis(), System.currentTimeMillis());
            Log.d("onStop() NOteFragment", "THE NoteActivity value for title is " + Title + "THE NoteActivity value for title is " + Content);

            DatabaseHelper db = new DatabaseHelper(getContext());

            if (validateNote())
                result = db.addNote(note);
            if (result == -1) {
                Log.d("OnStop() Save Status", "Save not Sucessfull");

            } else {
                hasSaved = true;
                Log.d("OnStop() Save Status", "Save was successful");


            }

        } else {

            //this code block if for if it was an existing Note therfore it Updates rather than Overwriting Save  as a new Note
            final Note note = new Note(Title, Content, System.currentTimeMillis(), System.currentTimeMillis());
            Log.d("onStop() NOteFragment", "THE NoteActivity value for title is " + Title + "THE NoteActivity value for title is " + Content);

            DatabaseHelper db = new DatabaseHelper(getContext());

            String[] id = {String.valueOf(Oid)};
            Log.d("intentExtraID", String.valueOf(Oid));
            long result = db.update(note, id);
            if (result < 1) {
                Log.d("OnStop() Save Status", "Save not successful");
                //Toast.makeText(getContext(), "Save was not Successfull", Toast.LENGTH_LONG).show();
            } else {
                Log.d("OnStop() Save Status", "Save was successful");
                //Toast.makeText(getContext(), "Save was Successfull", Toast.LENGTH_LONG).show();


            }
        }
    }

    private boolean validateNote() {
        boolean result;
        if(title.toString().equals("")){
            Toast.makeText(getContext(),"please input a title for your Note!",Toast.LENGTH_LONG).show();
            result = false;
        }
        else{
            result = true;
        }
        return result;
    }
}