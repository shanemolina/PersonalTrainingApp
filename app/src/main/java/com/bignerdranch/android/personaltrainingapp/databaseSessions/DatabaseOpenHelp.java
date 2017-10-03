package com.bignerdranch.android.personaltrainingapp.databaseSessions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shane on 9/30/2017.
 */

public class DatabaseOpenHelp extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "workouts.db";
    private static final int DATA_VERSION = 2;

    public DatabaseOpenHelp(Context context) {
        super(context, DATABASE_NAME, null, DATA_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Session(date TEXT PRIMARY KEY, workout TEXT, musclegroup TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
