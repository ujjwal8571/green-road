package com.example.android.roadsafety;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;

public class MarkerForm extends MainActivity implements OnItemSelectedListener
{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marker_form);

        ImageView image1 = (ImageView) findViewById(R.id.gmarker);

        TextView text1 = (TextView) findViewById(R.id.text1);

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.marker_choice, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((OnItemSelectedListener) this);

        TextView text2 = (TextView) findViewById(R.id.text2);

        EditText editText = (EditText) findViewById(R.id.editText);

        TextView text3 = (TextView) findViewById(R.id.text3);

        TextView text4 = (TextView) findViewById(R.id.text4);
        //add latitude longitude here
        TextView text5 = (TextView) findViewById(R.id.text5);

        EditText editText2 = (EditText) findViewById(R.id.editText2);

        TextView text6 = (TextView) findViewById(R.id.total_potholes);

        ImageView image2 = (ImageView) findViewById(R.id.camera);

        Button button1 = (Button) findViewById(R.id.bcamera);

        Button button2 = (Button) findViewById(R.id.bgallery);

    }

    public void onItemSelected(AdapterView<?> parent, View v,int pos,long id) {
        parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        parent.getFirstVisiblePosition();
    }
}
