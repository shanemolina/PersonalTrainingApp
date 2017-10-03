package com.bignerdranch.android.personaltrainingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.personaltrainingapp.database.DatabaseHandler;

public class Register extends AppCompatActivity {
    private Button mRegister;
    private EditText mUser_name, mPassword, mConfirm_password;
    private String user_name, user_pass, confirm_pass;
    private TextView mbackToLogin;
    DatabaseHandler helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        helper = new DatabaseHandler(this);
        mUser_name = (EditText)findViewById(R.id.reg_username);
        mPassword = (EditText) findViewById(R.id.reg_password);
        mConfirm_password = (EditText) findViewById(R.id.confirm_password);
        mRegister = (Button) findViewById(R.id.reg_button);
        mbackToLogin = (TextView) findViewById(R.id.backToLogin);
        mbackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, MainLogin.class);
                startActivity(intent);
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }
    private void register(){
        String username = mUser_name.getText().toString();
        String pass = mPassword.getText().toString();
        String confirm = mConfirm_password.getText().toString();

        if(!pass.equals(confirm)){
            Toast.makeText(getBaseContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
        }
        else{
            helper.addUser(username, pass);
            Toast.makeText(getBaseContext(), "User Registered", Toast.LENGTH_LONG).show();
        }
    }
}
