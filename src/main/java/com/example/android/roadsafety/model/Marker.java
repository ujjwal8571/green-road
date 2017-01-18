package com.example.android.roadsafety.model;



/**
 * Created by ujjwal on 15/1/17.
 */

import android.content.Context;

public class Marker {

    Integer id;
    String type;
    String name;
    String email;
    String latitude;
    String longitude;
    byte[] image;


    public Marker(String type, String name, String email, String latitude, String longitude, byte[] image) {
        this.type = type;
        this.name = name;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;

    }

    public Marker(){

    }

    public int getID()
    {
        return this.id;
    }

    public void setID(int ID)
    {
        this.id=ID;
    }

    public String getType() { return this.type;}

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getName() {return this.name;}

    public void setEmail(String email) {
        this.email=email;
    }

    public String getEmail() {return this.email;}

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {return this.latitude;}

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {return this.longitude;}

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {return this.image;}



}