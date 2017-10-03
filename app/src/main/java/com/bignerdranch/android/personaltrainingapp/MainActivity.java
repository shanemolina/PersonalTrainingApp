package com.bignerdranch.android.personaltrainingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.personaltrainingapp.databaseCustomer.Contact;
import com.bignerdranch.android.personaltrainingapp.databaseCustomer.DatabaseAccess;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Button btnAdd;
    private List<Contact> contacts;
    private DatabaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the GUI components
        this.listView = (ListView) findViewById(R.id.listView);
        this.btnAdd = (Button) findViewById(R.id.btnAdd);

        this.databaseAccess = DatabaseAccess.getInstance(getApplicationContext());

        //Set event listener to Button
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });

        // Set event listener to ListView
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateContact(position);
            }
        });
    }

    /**
     * Read all the contacts
     *
     * @return List of Contacts
     */

    private List<Contact> getContacts() {
        databaseAccess.open();
        List<Contact> list = databaseAccess.getContacts();
        databaseAccess.close();
        return list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListView();
    }

    private void updateListView() {
        this.contacts = getContacts();

        // Create the adapter and assign to ListView
        ContactAdapter adapter = new ContactAdapter(this, contacts);
        this.listView.setAdapter(adapter);
    }

    /**
     * Start ViewActivity to add new Contact.
     */
    private void addContact() {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    /**
     * Start ViewActivity to update a Contact.
     *
     * @param index the index of the contact
     */
    private void updateContact(int index) {
        Contact contact = contacts.get(index);
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("CONTACT", contact);
        startActivity(intent);
    }

    /**
     * Custom ArrayAdapter for Contacts.
     */
    private class ContactAdapter extends ArrayAdapter<Contact> {


        public ContactAdapter(Context context, List<Contact> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_view, parent, false);
            }
            TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
            TextView txtLast = (TextView) convertView.findViewById(R.id.txtLast);
            TextView txtPhone = (TextView) convertView.findViewById(R.id.txtPhone);
            Contact contact = contacts.get(position);
            txtName.setText(contact.getFirstName());
            txtLast.setText(contact.getLastName());
            txtPhone.setText(contact.getPhone());
            return convertView;
        }
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
                Intent intent = new Intent(MainActivity.this, MainLogin.class);
                startActivity(intent);
                //log off message
                CharSequence text = "Logging You Off!";
                Toast.makeText(this, text, Toast.LENGTH_SHORT)
                        .show();
        }
        return super.onOptionsItemSelected(item);
    }
}
