package com.bignerdranch.android.personaltrainingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import com.bignerdranch.android.personaltrainingapp.databaseSessions.Customer;
import com.bignerdranch.android.personaltrainingapp.databaseSessions.DataBaseAccess;

import java.util.List;

public class Session extends AppCompatActivity {

    private ListView listView;
    private Button btnAdd;
    private List<Customer> contacts;
    private DataBaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

    }
}
