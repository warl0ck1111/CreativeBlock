package com.warl0ck.creativeblock.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.warl0ck.creativeblock.Model.Note;

import java.util.ArrayList;

public class  DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "simple_note_app.db";
    public static final String TAG = DATABASE_NAME;
    private static final int DATABASE_VERSION = 4;
    Context context;
    private static final String CREATE_TABLE_NOTE = "create table "
            + Constants.NOTES_TABLE
            + "("
            + Constants.COLUMN_ID + " integer primary key autoincrement, "
            + Constants.COLUMN_TITLE + " text not null, "
            + Constants.COLUMN_CONTENT + " text not null, "
            + Constants.COLUMN_MODIFIED_TIME + " integer , "
            + Constants.COLUMN_CREATED_TIME + " integer not null" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        Log.d(TAG, "mDatabaseHelper's Constructor");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTE);
        Toast.makeText(context, "Table has been Created!!", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate was called and Executed Successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.NOTES_TABLE);
        onCreate(db);
        Log.d(TAG, "onUpdate");
    }


    /**
     * This method is to Create new Note records
     *
     * @return list
     */
    public long addNote(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constants.COLUMN_TITLE, note.getTitle());
        cv.put(Constants.COLUMN_CONTENT, note.getContent());
        cv.put(Constants.COLUMN_CREATED_TIME, note.getDateCreated());
        //cv.put(Constants.COLUMN_MODIFIED_TIME,note.getDataModified());
        long results = db.insert(Constants.NOTES_TABLE, null, cv);
        db.close();

        Log.d(TAG, "the addNote method returned: " + note.getContent()+"--> for getContrent()  & " + note.getTitle() + "--> for getTitle()  &"
                + note.getDataModified() +" for getDataModified" );
        return results;
    }


    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
   /* public List<Note> getAllRows(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query( Constants.NOTES_TABLE,  Constants.COLUMNS,
                null,
                null,
                null,
                null,
                 Constants.COLUMN_CREATED_TIME + " ASC");
        List<Note> noteList = new ArrayList<>();

        if(cursor.moveToFirst()) {

            do {
                Note notes = new Note();
                notes.setId(Long.parseLong(cursor.getString(cursor.getColumnIndex( Constants.COLUMN_ID))));
                notes.setTitle(cursor.getString(cursor.getColumnIndex( Constants.COLUMN_TITLE)));
                notes.setContent(cursor.getString(cursor.getColumnIndex( Constants.COLUMN_CONTENT)));
                notes.setDateCreated(Long.parseLong(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_CREATED_TIME))));
                noteList.add(notes);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        Log.d(TAG,"the getallRows method returned: "+String.valueOf(noteList));
        return  noteList;
    }
*/

    /**
     * This method is for deleting records from the database
     *
     * @param id
     * @returns number of rows affected, 0 otherwise.
     * To remove all rows and get a count pass "1" as the
     * whereClause.
     */
    public int deleteUser(long id) {

        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id


        int result = db.delete(Constants.NOTES_TABLE, Constants.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        Log.d(TAG, "the deleteUser method returned: " + String.valueOf(result));

        return result;
    }

/**
     * This method is for deleting records from the database
     *
     * @param title
     * @returns number of rows affected, 0 otherwise.
     * To remove all rows and get a count pass "1" as the
     * whereClause.
     */
    public int deleteUser(String title) {

        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        int result = db.delete(Constants.NOTES_TABLE, Constants.COLUMN_ID + " = ?",
                new String[]{String.valueOf(title)});
        db.close();
        Log.d(TAG, "the deleteUser method returned: " + String.valueOf(result));

        return result;
    }


    /**
     * This method is for updating records on the database
     *
     * @param note
     * @returns number of rows affected
     */
    public int update(Note note, String[] id) {
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_TITLE, note.getTitle());
        values.put(Constants.COLUMN_CONTENT, note.getContent());
        //values.put(Constants.COLUMN_CREATED_TIME, System.currentTimeMillis());
        values.put(Constants.COLUMN_MODIFIED_TIME, System.currentTimeMillis());
        /*mContext.getContentResolver().update(NoteContentProvider.CONTENT_URI,
                values, Constants.COLUMN_ID  + "=" + note.getId(), null);*/
        SQLiteDatabase db = getWritableDatabase();
        int result = db.updateWithOnConflict(Constants.NOTES_TABLE, values, Constants.COLUMN_ID + "= ?", id,0);
        db.close();

        Log.d(TAG, "the Update method returned: " + String.valueOf(result));

        return result;

    }


    /**
     * this method queries the DataBase and
     *
     * @return cursor
     */
    public Cursor getRows() {
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = Constants.COLUMNS;

        String sqlStatement = " Select * from " + Constants.NOTES_TABLE;
        Cursor cursor = db.rawQuery(sqlStatement, null);
        //(TABLE_NAME, colums, null, null, null, null, null);
        //db.query(true, TABLE_NAME,colums,null,null,null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();

        return cursor;
    }



    public ArrayList<Note> getAllData() {
        ArrayList<Note> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String sqlStatement = " Select * from " + Constants.NOTES_TABLE;
        Cursor cursor = db.rawQuery(sqlStatement, null);
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(Constants.COLUMN_ID);
            int index2 = cursor.getColumnIndex(Constants.COLUMN_TITLE);
            int index3 = cursor.getColumnIndex(Constants.COLUMN_CONTENT);
            int index4 = cursor.getColumnIndex(Constants.COLUMN_CREATED_TIME);
            int index5 = cursor.getColumnIndex(Constants.COLUMN_MODIFIED_TIME);

            long cid = cursor.getLong(index);
            String title = cursor.getString(index2);
            String content = cursor.getString(index3);
            long modified_Date = cursor.getLong(index5);

            long date = cursor.getLong(index4);

            Log.d(TAG, "THE Databasehelper getAllData array value for title is " + title + "THE getAllData array value for content is " + content + "THE getAllData array value for date is " + date);
            Note note = new Note(cid,title, content, date, modified_Date);
           /* note.setTitle(title);
            note.setContent(content);
            note.setDateCreated(date);*/
            list.add(note);
        }
        db.close();

        Log.d(TAG, "THE getAllData returned " + list.size());

        return list;
    }

    public Cursor getData(long id){
        SQLiteDatabase db = getReadableDatabase();
        String selection = Constants.COLUMN_ID  + "=?";
        String[] selectArgs = {String.valueOf(id)};
        Cursor cursor = db.query(Constants.NOTES_TABLE,Constants.COLUMNS,selection,selectArgs,null,null,null);
        db.close();

        return cursor;
    }















































        /* *//**
         * This method to check user exist or not
         *
         * @param email
         * @param password
         * @return true/false
         *//*
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        *//**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         *//*
        Cursor cursor = db.query(Constants.NOTES_TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
*/



}