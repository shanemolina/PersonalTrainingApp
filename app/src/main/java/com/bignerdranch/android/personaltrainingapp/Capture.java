package com.bignerdranch.android.personaltrainingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Capture extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.LogOf:
                Intent intent = new Intent(Capture.this, MainLogin.class);
                startActivity(intent);
                //log off message
                CharSequence text = "Logging You Off!";
                Toast.makeText(this, text, Toast.LENGTH_SHORT)
                        .show();
        }
        return super.onOptionsItemSelected(item);
    }
}
