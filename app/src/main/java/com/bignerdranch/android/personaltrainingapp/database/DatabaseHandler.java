package com.bignerdranch.android.personaltrainingapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by shane on 9/30/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int database_version = 3;
    private static final String DATABASE_NAME = "accounts";

    //Table for username and password.
    private static final String TABLE_NAME = "userdata";
    private static final String USER_NAME = "username";
    private static final String USER_PASSWORD = "password";
    private static final String USER_ID = "ID";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_NAME + " TEXT, "
            + USER_PASSWORD + " TEXT);";
    //______________________________________________________________________________________________
    SQLiteDatabase db;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database created");
    }
    //______________________________________________________________________________________________
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d("Database operations", "Table created");
    }
    //______________________________________________________________________________________________
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //______________________________________________________________________________________________
    public void addUser(String user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, user);
        contentValues.put(USER_PASSWORD, password);
        long id = db.insert(TABLE_NAME, null, contentValues);
        db.close();


        Log.d("Database operations", "One row inserted");
    }
    //______________________________________________________________________________________________
    public boolean getUser(String username, String pass) {
        String selectQuery = "select * from " + TABLE_NAME + " where " + USER_NAME + " = " + "'" + username + "'" + " and " +
                USER_PASSWORD + " = " + "'" + pass + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }
}
