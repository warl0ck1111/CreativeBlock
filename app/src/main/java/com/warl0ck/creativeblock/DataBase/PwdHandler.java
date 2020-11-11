package com.warl0ck.creativeblock.DataBase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class PwdHandler extends SQLiteOpenHelper {
    final String DATABASENAME = "passwordHandler";
    final String COL_USERNAME = "USERNAME";
    final String COL_PASSWORD = "PASSWORD";
    final String SQLstatement = "CREATE DATABASE " + DATABASENAME



    public PwdHandler(@androidx.annotation.Nullable @Nullable Context context, @androidx.annotation.Nullable @Nullable String name, @androidx.annotation.Nullable @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}