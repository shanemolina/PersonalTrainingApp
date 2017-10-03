package com.bignerdranch.android.personaltrainingapp;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bignerdranch.android.personaltrainingapp.databaseCustomer.Contact;
import com.bignerdranch.android.personaltrainingapp.databaseCustomer.DatabaseAccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shane on 9/30/2017.
 */

public class ViewActivity extends AppCompatActivity {

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etPhone;
    private EditText etEmail;
    private Button btnSave;
    private Button btnDelete;
    private ImageButton btnCapture;
    private Button btnSession;
    private Contact contact;
    private DatabaseAccess databaseAccess;
    private ImageView mImgPhoto;
    String mCurrentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        // Find the GUI components
        findViews();

        checkIntentForContact();

        this.databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        mImgPhoto = (ImageView) findViewById(R.id.photo);

        this.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contact == null) {
                    insertContact();
                } else {
                    updateContact();
                }
                saveToInternalStorage();
                loadImageFromStorage();
            }
        });

        this.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact();
            }
        });

        this.btnSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewActivity.this, Session.class);
                startActivity(intent);
            }
        });

        //get access to the image view


        btnCapture = (ImageButton) findViewById(R.id.imageButton);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
                saveToInternalStorage();
                loadImageFromStorage();
        }

        });

    }

//___________________________________________________________________________________________________

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImgPhoto.setImageBitmap(imageBitmap);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
           /**
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {


            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.bignerdranch.android.personaltrainingapp.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);**/
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }

    private String saveToInternalStorage(){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("images", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            Bitmap bitmapImage = null;
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
    private void loadImageFromStorage()
    {

        try {
            String path = null;
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            mImgPhoto.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
    private void updateContact() {
        databaseAccess.open();
        Contact newContact = new Contact();
        newContact.setFirstName(etFirstName.getText().toString());
        newContact.setLastName(etLastName.getText().toString());
        newContact.setPhone(etPhone.getText().toString());
        newContact.setEmail(etEmail.getText().toString());

        databaseAccess.updateContact(contact, newContact);
        databaseAccess.close();
        this.finish();
    }

    private void insertContact() {
        databaseAccess.open();
        Contact newContact = new Contact();
        newContact.setFirstName(etFirstName.getText().toString());
        newContact.setLastName(etLastName.getText().toString());
        newContact.setPhone(etPhone.getText().toString());
        newContact.setEmail(etEmail.getText().toString());

        databaseAccess.insertContact(newContact);
        databaseAccess.close();
        this.finish();
    }

    private void deleteContact() {
        databaseAccess.open();
        databaseAccess.deleteContact(contact);
        databaseAccess.close();
        this.finish();
    }

    private void findViews() {
        this.etFirstName = (EditText) findViewById(R.id.etFirstName);
        this.etLastName = (EditText) findViewById(R.id.etLastName);
        this.etPhone = (EditText) findViewById(R.id.etPhone);
        this.etEmail = (EditText) findViewById(R.id.etEmail);
        this.btnSave = (Button) findViewById(R.id.btnSave);
        this.btnDelete = (Button) findViewById(R.id.btnDelete);
        this.btnCapture = (ImageButton) findViewById(R.id.imageButton);
        this.btnSession = (Button) findViewById(R.id.sessionButton);
    }

    private void checkIntentForContact() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        btnDelete.setEnabled(false);
        if (bundle != null) {
            contact = (Contact) bundle.get("CONTACT");
            if (contact != null) {
                this.etFirstName.setText(contact.getFirstName());
                this.etLastName.setText(contact.getLastName());
                this.etPhone.setText(contact.getPhone());
                this.etEmail.setText(contact.getEmail());
                btnDelete.setEnabled(true);
            }
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
                Intent intent = new Intent(ViewActivity.this, MainLogin.class);
                startActivity(intent);
                //log off message
                CharSequence text = "Logging You Off!";
                Toast.makeText(this, text, Toast.LENGTH_SHORT)
                        .show();
        }
        return super.onOptionsItemSelected(item);
    }
}
