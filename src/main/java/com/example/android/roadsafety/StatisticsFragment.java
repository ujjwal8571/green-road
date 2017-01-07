package com.example.android.roadsafety;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
//public class StatisticsFragment extends MainActivity {
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.statisticsfragment);
//
//        TextView text1 = (TextView) findViewById(R.id.text1);
//
//        ImageView image1 = (ImageView) findViewById(R.id.winner);
//
//        TextView text2 = (TextView) findViewById(R.id.text2);
//
//        TextView text3 = (TextView) findViewById(R.id.text3);
//
//        TextView text4 = (TextView) findViewById(R.id.text4);
//
//        TextView pothole1 = (TextView) findViewById(R.id.pothole1);
//
//        TextView pothole2 = (TextView) findViewById(R.id.pothole2);
//
//        TextView bump1 = (TextView) findViewById(R.id.bump1);
//
//        TextView bump2 = (TextView) findViewById(R.id.bump2);
//
//        TextView solved1 = (TextView) findViewById(R.id.solved1);
//
//        TextView solved2 = (TextView) findViewById(R.id.solved2);
//
//        TextView text5 = (TextView) findViewById(R.id.text5);
//
//        TextView total_potholes = (TextView) findViewById(R.id.total_potholes);
//
//        TextView total_bumps = (TextView) findViewById(R.id.total_bumps);
//
//        TextView total_solved = (TextView) findViewById(R.id.total_solved);
//
//        TextView total_p = (TextView) findViewById(R.id.total_p);
//
//        TextView total_b = (TextView) findViewById(R.id.total_b);
//
//        TextView total_s = (TextView) findViewById(R.id.total_s);
//
//        TextView toolbar = (TextView) findViewById(R.id.toolbar);
//    }

    public class StatisticsFragment extends Fragment {




    public StatisticsFragment() {
        // Required empty public constructor
    }
    public static StatisticsFragment newInstance() {

        Bundle args = new Bundle();

        StatisticsFragment fragment = new StatisticsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private TextView test;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.statisticsfragment, container, false);
        return rootView;
    }

}
