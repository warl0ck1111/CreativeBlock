package com.warl0ck.creativeblock.Model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.warl0ck.creativeblock.Activities.NoteActivity;
import com.warl0ck.creativeblock.DataBase.DatabaseHelper;
import com.warl0ck.creativeblock.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static android.support.v4.content.ContextCompat.startActivity;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.MyRecyclerView> {

    private ArrayList<Note> noteArrayList;
    public static final String TAG = "NoteRecyclerAdapter";
    Context mcontext;

    private ArrayList<Note> newArrayList;
    long id;

    public NoteRecyclerAdapter(ArrayList<Note> noteArrayList) {

        this.noteArrayList = noteArrayList;
    }

    @Override
    public MyRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        mcontext = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);
        MyRecyclerView recyclerViewHolder = new MyRecyclerView(itemView);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerView holder, final int position) {


        final Note current = noteArrayList.get(position);
        Log.d(TAG, "NoteRecyclerAdapter's onBindViewHolder variable current size " + noteArrayList.size() +
                "\n and the position Value is " + position);

        if (current.getDataModified()==0){
            holder.tvDateModified.setVisibility(View.INVISIBLE);
            holder.tvDm.setVisibility(View.INVISIBLE);
        }
        holder.tvDateCreated.setText(getFormattedDate(String.valueOf(current.getDateCreated())));
        holder.tvTitile.setText(current.getTitle());
        holder.tvDateModified.setText(getFormattedDate(String.valueOf(current.getDataModified())));
        // id used for the delete
        final long mId = current.getId();

        // delete Item on list(Not Functioning well)
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String id = holder.edId.getText().toString();
                DatabaseHelper db = new DatabaseHelper(mcontext);
                int result = db.deleteUser(mId);
                ArrayList<Note> newArrayList = new ArrayList<Note>();
                DatabaseHelper dbHelper = new DatabaseHelper(mcontext);
                newArrayList = dbHelper.getAllData();
                noteArrayList = newArrayList;

                notifyDataSetChanged(); //this method is not applicable here because the structure of this recyclerView is respective to the size of array list
                System.out.println("the delete button returned " + result);

            }
        });

        holder.tvTitile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcontext, NoteActivity.class);

                String Ititle = current.getTitle();
                String Icontent = current.getContent();
                String Iid = String.valueOf(current.getId());
                Log.d("NRAdapter getid()", Iid);
                Log.d("NRAdapter Icontent()", Icontent);
                Log.d("NRAdapter Ititle()", Ititle);

                i.putExtra("TITLE", Ititle);
                i.putExtra("CONTENT", Icontent);
                i.putExtra("ID", Iid);
                //notifyItemChanged(position);
                startActivity(mcontext, i, null);

                Log.d("Intent", Ititle);
                Log.d("Intent", Icontent);
            }
        });
    }

    private String getFormattedDate(String milliSeconds) {
        // Create a DateFormatter object for displaying date in specified format.
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(milliSeconds));
        return formatter.format(calendar.getTime());
    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }


    public static class MyRecyclerView extends RecyclerView.ViewHolder {

        public TextView tvTitile, tvDm, tvDateCreated, tvDateModified;
        public EditText edId;
        public Button delete;

        public MyRecyclerView(View itemView) {
            super(itemView);
            tvDateCreated = itemView.findViewById(R.id.tvDateCreated);
            tvTitile = itemView.findViewById(R.id.tvTitle);
            tvDm = itemView.findViewById(R.id.tvDm);

            tvDateModified = itemView.findViewById(R.id.tvDateModified);
            edId = itemView.findViewById(R.id.etId);
            delete = itemView.findViewById(R.id.deleteNote);

        }

    }
}