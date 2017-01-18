package com.example.android.roadsafety;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.ads.formats.NativeAd;
//import com.google.android.gms.maps.model.Marker;
import com.example.android.roadsafety.model.Marker;

import java.sql.Array;
import java.util.ArrayList;


/**
 * Created by ujjwal on 16/1/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String dbName = "greenRoadDB";
    static final String markerTable = "markers";
    static final String colID = "markerID";
    static final String colType = "markerType";
    static final String colName = "markerName";
    static final String colEmail = "markerEmail";
    static final String colLatitude = "markerLatitutde";
    static final String colLongitude = "markerLongitude";
    static final String colImage = "markerImage";

    static final String viewmarker="ViewMarker";

    public Cursor cursor;

    public DatabaseHelper(Context context) {
        super(context,dbName,null,33);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+ markerTable +"( "+colID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                colType +" TEXT, "+colName+ " TEXT, "+ colEmail + " TEXT, "+ colLatitude+ " DOUBLE, "+
                colLongitude+ " DOUBLE, "+ colImage+ " BLOB)");



//        db.execSQL("CREATE VIEW "+viewmarker+
//                "AS SELECT "+markerTable+"."+colID+"AS _id,"+
//                " "+markerTable+"."+colType+","+
//                " "+markerTable+"."+colName+","+
//                " "+markerTable+"."+colEmail+","+
//                " "+markerTable+"."+colLatitude+","+
//                " "+markerTable+"."+colLongitude+","+
//                " "+markerTable+"."+colImage);
//

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        db.execSQL("DROP TABLE IF EXISTS "+markerTable);


        db.execSQL("DROP VIEW IF EXISTS "+viewmarker);
        onCreate(db);
    }


    void AddMarker(Marker marker) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(colType,marker.getType());
        cv.put(colName,marker.getName());
        cv.put(colEmail,marker.getEmail());
        cv.put(colLatitude,marker.getLatitude());
        cv.put(colLongitude,marker.getLongitude());
        cv.put(colImage, marker.getImage());
        Log.i("MainActivity",cv.toString());
        db.insert(markerTable, null, cv);
        Log.i("MainActivity",db.toString());





        db.close();


    }



    public ArrayList<String> getAllMarkers() {
        ArrayList<String> values = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT markerName FROM " + markerTable, null);
        int a= cursor.getCount();
        Log.i("MainActivity",Integer.toString(a));

//        if (cursor.moveToFirst()) {
//            do {
//                values.add(cursor.getString(cursor.getColumnIndex(colID)));
//            } while (cursor.moveToNext());
//        }

        if(cursor!=null) {
            if(cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex("markerName"));
                    Log.i("MainActivity",name);

                }while(cursor.moveToNext());
            }
        }

        cursor.close();
        db.close();


        return values;

    }







}
