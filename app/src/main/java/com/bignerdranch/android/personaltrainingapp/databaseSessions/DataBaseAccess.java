package com.bignerdranch.android.personaltrainingapp.databaseSessions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shane on 9/30/2017.
 */

public class DataBaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DataBaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DataBaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelp(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DataBaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }




    public void insertWorkout(Customer session) {
        ContentValues values = new ContentValues();
        values.put("date", session.getDate());
        values.put("workout", session.getWorkout());
        values.put("musclegroup", session.getWorkout());
        database.insert("Session", null, values);
    }

    public List<Customer> getWorkouts() {
        List<Customer> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Session", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Customer session = new Customer();
            session.setDate(cursor.getString(0));
            session.setWorkout(cursor.getString(1));
            session.setMuscle(cursor.getString(2));
            list.add(session);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    public void updateWorkout(Customer oldWorkout, Customer  newWorkout) {
        ContentValues values = new ContentValues();
        values.put("date", newWorkout.getDate());
        values.put("workout", newWorkout.getWorkout());
        values.put("musclegroup", newWorkout.getMuscle());
        database.update("Session", values, "date = ?", new String[]{oldWorkout.getWorkout()});
    }


    public void deleteWorkout(Customer session) {
        database.delete("Session", "date = ?", new String[]{session.getWorkout()});
        database.close();
    }


}
