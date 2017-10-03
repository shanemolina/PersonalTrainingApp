package com.bignerdranch.android.personaltrainingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.bignerdranch.android.personaltrainingapp.databaseSessions.Customer;
import com.bignerdranch.android.personaltrainingapp.databaseSessions.DataBaseAccess;

public class SessionAdd extends AppCompatActivity {

    private EditText mDate;
    private EditText mWorkout;
    private EditText mMuscle;
    private Button btnSave;
    private Button btnDelete;
    private Customer contact;
    private DataBaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_add);
    }
}