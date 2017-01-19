package com.example.android.roadsafety;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.Manifest;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.example.android.roadsafety.model.Marker;
import com.google.android.gms.maps.model.LatLng;

public class MarkerForm extends MainActivity implements OnItemSelectedListener {
    private static final int CAMERA_REQUEST = 1888;
    public static final int REQUEST_IMAGE_SELECTOR = 1887;
    private ImageView imageView;
    public Bitmap image;

    DatabaseHelper dbHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marker_form);

        Bundle bundle = getIntent().getExtras();
            final Double latitude = bundle.getDouble("latitude");
            final Double longitude = bundle.getDouble("longitude");




        this.imageView = (ImageView) findViewById(R.id.gmarker);



        final Spinner spinner = (Spinner) findViewById(R.id.selecttype);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.marker_choice, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((OnItemSelectedListener) this);


        final EditText markerName = (EditText) findViewById(R.id.entername);

        TextView currentLocation = (TextView) findViewById(R.id.yourlocation);

        currentLocation.setText(Double.toString(latitude) + ',' + Double.toString(longitude));



        final EditText markerUserEmail = (EditText) findViewById(R.id.enteremail);

        Button cameraButton = (Button) findViewById(R.id.bcamera);

        Button galleryButton = (Button) findViewById(R.id.bgallery);

        Button submitButton = (Button) findViewById(R.id.submit);

        ImageView imageView = (ImageView) findViewById(R.id.gmarker);

        dbHelper = new DatabaseHelper(this);




        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMarker(spinner,markerName,markerUserEmail,latitude,longitude);

                Intent backToMainActivity = new Intent(MarkerForm.this, MainActivity.class );
                startActivity(backToMainActivity);
            }
        });

        galleryButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {



                if (ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(MarkerForm.this,new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_SELECTOR);
                }

                Intent intent = new Intent();

                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_IMAGE_SELECTOR);
            }

        });


        cameraButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);


            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            image = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(image);

        } else if (requestCode == REQUEST_IMAGE_SELECTOR && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();

            try {
                image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));


                imageView.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
        parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        parent.getFirstVisiblePosition();
    }


    @Override
    public void onBackPressed() {

        Intent backPressedIntent = new Intent(MarkerForm.this, MainActivity.class);
        startActivity(backPressedIntent);
    }


    public void addMarker(Spinner spinner, EditText markerName, EditText userEmail, Double latitude, Double longitude) {
        String spinnerText = spinner.getSelectedItem().toString();
        String markerNameText = markerName.getText().toString();
        String userEmailText = userEmail.getText().toString();
        String markerLatitude = Double.toString(latitude);
        String markerLongitude = Double.toString(longitude);

        Bitmap markerImage = image;
//        Bitmap b = BitmapFactory.decodeResource(getResources(),
//                R.drawable.kitkat);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        markerImage.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] img = bos.toByteArray();

        Marker marker = new Marker();

        marker.setType(spinnerText);
        marker.setName(markerNameText);
        marker.setEmail(userEmailText);
        marker.setLatitude(markerLatitude);
        marker.setLongitude(markerLongitude);
        marker.setImage(img);

        dbHelper.AddMarker(marker);




    }
}
