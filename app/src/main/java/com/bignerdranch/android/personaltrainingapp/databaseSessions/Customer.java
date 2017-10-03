package com.bignerdranch.android.personaltrainingapp.databaseSessions;

import java.io.Serializable;

/**
 * Created by shane on 9/30/2017.
 */

public class Customer implements Serializable {

    String mDate, mWorkout, mMuscle;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getWorkout() {
        return mWorkout;
    }

    public void setWorkout(String workout) {
        mWorkout = workout;
    }

    public String getMuscle() {
        return mMuscle;
    }

    public void setMuscle(String muscle) {
        mMuscle = muscle;
    }

    @Override
    public String toString() {
        return mWorkout + " " + mMuscle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer workout = (Customer) o;

        if (!mDate.equals(workout.mDate)) return false;

        return true;
    }
}
