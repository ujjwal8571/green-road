package com.example.android.roadsafety;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;

public class MarkerForm extends MainActivity implements OnItemSelectedListener
{
    private static final int CAMERA_REQUEST = 1888;
    public static final int REQUEST_IMAGE_SELECTOR = 1887;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marker_form);

        this.imageView = (ImageView) findViewById(R.id.gmarker);

        //TextView text1 = (TextView) findViewById(R.id.text1);

        Spinner spinner = (Spinner) findViewById(R.id.selecttype);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.marker_choice, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((OnItemSelectedListener) this);

        //TextView text2 = (TextView) findViewById(R.id.text2);

        EditText markerName = (EditText) findViewById(R.id.entername);

        TextView currentLocation = (TextView) findViewById(R.id.yourlocation);

//        TextView text4 = (TextView) findViewById(R.id.text4);
//        //add latitude longitude here
//        TextView text5 = (TextView) findViewById(R.id.text5);

        EditText markerUserEmail = (EditText) findViewById(R.id.enteremail);

        //TextView text6 = (TextView) findViewById(R.id.total_potholes);

        //ImageView image2 = (ImageView) findViewById(R.id.camera);

        Button cameraButton = (Button) findViewById(R.id.bcamera);

        Button galleryButton = (Button) findViewById(R.id.bgallery);

        Button submitButton = (Button) findViewById(R.id.submit);

        ImageView imageView = (ImageView) findViewById(R.id.gmarker);

        galleryButton.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_IMAGE_SELECTOR);
            }

        });


        cameraButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);


            }
        });



    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }

        else if(requestCode == REQUEST_IMAGE_SELECTOR && resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));


                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void onItemSelected(AdapterView<?> parent, View v,int pos,long id) {
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
}
