package com.example.android.roadsafety;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//public class AboutFragment extends MainActivity {
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.aboutfragment);
//
//    TextView about = (TextView) findViewById(R.id.about);
//    TextView aboutinfo = (TextView) findViewById(R.id.aboutapp);
//    about.setMovementMethod(new ScrollingMovementMethod());


public class AboutFragment extends Fragment {


    public AboutFragment() {
        // Required empty public constructor
    }
    public static AboutFragment newInstance() {

        Bundle args = new Bundle();

        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private TextView test;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about_fragment, container, false);
        return rootView;
    }

}
