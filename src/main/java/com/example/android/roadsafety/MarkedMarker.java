package com.example.android.roadsafety;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class MarkedMarker extends MainActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marker_marked);

        ImageView image1 = (ImageView) findViewById(R.id.micon);

        TextView text1 = (TextView) findViewById(R.id.name);

        ImageView image2 = (ImageView) findViewById(R.id.smarker);

        TextView text2 = (TextView) findViewById(R.id.type);

        Button button1 = (Button) findViewById(R.id.likeButton);

        Button button2 = (Button) findViewById(R.id.fakeButton);

        Button button3 = (Button) findViewById(R.id.shareButton);

        ImageView image3 = (ImageView) findViewById(R.id.like);

        ImageView image4 = (ImageView) findViewById(R.id.fake);

        ImageView image5 = (ImageView) findViewById(R.id.share);

        Bundle bundle = getIntent().getExtras();
        final Double latitude = bundle.getDouble("latitude");
        final Double longitude = bundle.getDouble("longitude");

        ArrayList arrayList = dbHelper.getMarkerText(latitude,longitude);

        Object[] mStringArray = arrayList.toArray();

        text1.setText((String) mStringArray[1]);
        text2.setText((String) mStringArray[2]);

        Log.i("MarkerActivity", (String)mStringArray[1]);
        Log.i("MarkerActivity", (String)mStringArray[2]);

        byte[] blob = dbHelper.getMarkerImage(latitude,longitude);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);


        image1.setImageBitmap(bitmap);


    }



    @Override
    public void onBackPressed() {

        Intent backPressedIntent = new Intent(MarkedMarker.this, MainActivity.class);
        startActivity(backPressedIntent);
    }

}
