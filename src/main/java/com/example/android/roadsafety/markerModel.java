package com.example.android.roadsafety;



/**
 * Created by ujjwal on 15/1/17.
 */

public class markerModel {

    String type;
    String name;
    String email;
    Double latitude;
    Double longitude;
    String imageUrl;


    public markerModel(String type, String name, String email, Double latitude, Double longitude, String imageUrl) {
        this.type = type;
        this.name = name;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageUrl = imageUrl;

    }

    public markerModel(){

    }

    public String getType() { return type;}

    public String getName() {return name;}

    public String getEmail() {return email;}

    public Double getLatitude() {return latitude;}

    public Double getLongitude() {return longitude;}

    public String getImageUrl() {return imageUrl;}



}