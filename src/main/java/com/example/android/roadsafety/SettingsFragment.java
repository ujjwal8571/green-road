package com.example.android.roadsafety;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.preference.PreferenceActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

//public class AboutFragment extends MainActivity {
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.aboutfragment);
//
//    TextView alert = (TextView) findViewById(R.id.alert);
//    TextView alertdistance = (TextView) findViewById(R.id.alertdistance);
//    CheckBox checkbox = (CheckBox) findViewById(R.id.checkboxforalert);
//    EditText enterdistance = (EditText) findViewById(R.id.enteralertdistance);
//    Button applychanges = (Button) findViewById(R.id.applychanges);


public class SettingsFragment extends PreferenceActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }

}