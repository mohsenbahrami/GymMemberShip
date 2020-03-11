package com.example.gymmembership;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PageMember extends AppCompatActivity {
    int REQUEST_SCAN_CODE = 1;
    int REQUEST_GRAB_PICTURE_CODE = 2;
    int REQUEST_TAKE_PHOTO = 3;
    String currentPhotoPath;
    ImageView imageView;
    Context context;
    CustomListAdapter adapter;
    ArrayList<Member> members;
    Member member;
    ListView listView;
    String testFilePath = "/storage/emulated/0/Android/data/com.example.GymMemberShip/files/Pictures/JPEG_20200306_084907_2487466390706180963.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_member);
        imageView = (ImageView) findViewById(R.id.imageView_member);
         //adding to listView
        members = new ArrayList<Member>();
        adapter = new CustomListAdapter(this, members);
        listView = (ListView) findViewById(R.id.ls_members);
     //   listView.setAdapter(adapter);
        context = this;


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        //QED: create the image from the camera and save it and in order to be use later on
        try{
            setImageFromURI(testFilePath, 500, imageView);
        }catch (Exception e){
            //in case image doesn't exist
            Toast.makeText(this, "Sorry, this path doesn't exist", Toast.LENGTH_SHORT).show();
        }
    }


    public void scan(View v){
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        //intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, REQUEST_SCAN_CODE);
    }

    public void grabPicture(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, REQUEST_GRAB_PICTURE_CODE);
    }

    public void dispatchTakePictureIntent(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                //send an URI to the camera in order to save the image in the given path
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.lecture5",
                        photoFile);

                //debug purpose: to view the litteral path
                Log.d("PHOTOURI", photoFile.toString());
                Log.d("PHOTOFILEPATH", currentPhotoPath);

                //important we need to put the photouri when startin the activity
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
       super.onActivityResult(requestCode,resultCode, intent);
        if (requestCode == REQUEST_SCAN_CODE && resultCode == RESULT_OK) {
            String contents = intent.getStringExtra("SCAN_RESULT");
            String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
            Toast.makeText(this, contents, Toast.LENGTH_SHORT).show();
        }else if (requestCode == REQUEST_GRAB_PICTURE_CODE && resultCode == RESULT_OK) {
            Bundle extras = intent.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }else if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            setImageFromURI(currentPhotoPath, 500, imageView); //neat function to set an image from a file path and also scrop and scale
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Cancelled Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void setImageFromURI(String filePath, int scale, ImageView imageView){
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(filePath);;
        bitmap = cropAndScale(bitmap, scale); // if you mind scaling
        imageView.setImageBitmap(bitmap);
    }

    public static  Bitmap cropAndScale (Bitmap source, int scale){
        int factor = source.getHeight() <= source.getWidth() ? source.getHeight(): source.getWidth();
        int longer = source.getHeight() >= source.getWidth() ? source.getHeight(): source.getWidth();
        int x = source.getHeight() >= source.getWidth() ?0:(longer-factor)/2;
        int y = source.getHeight() <= source.getWidth() ?0:(longer-factor)/2;
        source = Bitmap.createBitmap(source, x, y, factor, factor);
        source = Bitmap.createScaledBitmap(source, scale, scale, false);
        return source;
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
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}

