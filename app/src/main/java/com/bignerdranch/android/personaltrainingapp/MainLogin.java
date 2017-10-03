package com.bignerdranch.android.personaltrainingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.personaltrainingapp.database.DatabaseHandler;

public class MainLogin extends AppCompatActivity {

    private TextView mName;
    private TextView mPassword;
    private Button mLogin;
    private Button mRegister;
    DatabaseHandler helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

            helper = new DatabaseHandler(this);
            mName = (TextView) findViewById(R.id.et_username);
            mPassword = (TextView) findViewById(R.id.et_password);
            mRegister = (Button) findViewById(R.id.log_in_register_button);
            mRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainLogin.this, Register.class);
                    startActivity(intent);
                }
            });
            mLogin = (Button) findViewById(R.id.login_button);
            mLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    login();
                }
            });

        }

    private void login() {
        String username = mName.getText().toString();
        String pass = mPassword.getText().toString();

        if (helper.getUser(username, pass)) {
            startActivity(new Intent(MainLogin.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Username or Password do not match", Toast.LENGTH_LONG).show();
        }
    }
}
