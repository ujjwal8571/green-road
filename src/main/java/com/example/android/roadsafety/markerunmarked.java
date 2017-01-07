package com.example.android.roadsafety;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class markerunmarked extends MainActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marker_unmarked);

        ImageView image1 = (ImageView) findViewById(R.id.micon);

        TextView text1 = (TextView) findViewById(R.id.text1);

        ImageView image2 = (ImageView) findViewById(R.id.smarker);

        TextView text2 = (TextView) findViewById(R.id.text2);

        Button button1 = (Button) findViewById(R.id.likeButton);

        Button button2 = (Button) findViewById(R.id.fakeButton);

        Button button3 = (Button) findViewById(R.id.shareButton);

        ImageView image3 = (ImageView) findViewById(R.id.like);

        ImageView image4 = (ImageView) findViewById(R.id.fake);

        ImageView image5 = (ImageView) findViewById(R.id.share);

    }

}