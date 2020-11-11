package com.warl0ck.creativeblock.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.EditText;

import com.warl0ck.creativeblock.Fragments.NoteActivityFragment;
import com.warl0ck.creativeblock.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NoteActivity extends AppCompatActivity {

     EditText title;
     EditText content;
     NoteActivityFragment mFragment = new NoteActivityFragment();
     FragmentManager fm = getSupportFragmentManager();
     FragmentTransaction ft = fm.beginTransaction();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intentThatStartedThisActivity = getIntent();

        setContentView(R.layout.fragment_container);

        Fragment mFragment = new NoteActivityFragment();
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
            NoteActivityFragment firstFragment = new NoteActivityFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction()
                    .replace(R.id.mContainer, firstFragment);
            ft.addToBackStack("mNothing");

        } else {
            NoteActivityFragment myFragment2 = new NoteActivityFragment();
            //fm = getSupportFragmentManager();
            ft = ft.replace(R.id.mContainer, myFragment2);
            ft.addToBackStack("mNothingh");

        }
        ft.commit();
    }

    // supossed to give actual date of the day but Calendar was used instead..wait why do i still have it again even?
    public String today(){

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) +1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Date time = calendar.getTime();
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDateString = formatter.format(time);

        //return String.format("%d:%d:%d:" + time,day,month,year);
        return formattedDateString;


        //return String.format("%d:%d:%d:" + time,day,month,year);
        //return time;
    }

}
