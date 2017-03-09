package com.azurehorsecreations.signpost;

import android.graphics.drawable.Drawable;

/**
 * Created by pattycase on 10/8/16.
 */
public class City {
    private int flagResourceId;
    private String cityName;
    private double latitude;
    private double longitude;

    public City(int resourceId, String name, double latitude, double longitude) {
        this.flagResourceId = resourceId;
        this.cityName = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getFlagResourceId() {
        return flagResourceId;
    }

    public void setFlagResourceId(int resourceId) {
        this.flagResourceId = flagResourceId;
    }
    public String getName() {
        return cityName;
    }

    public void setName(String name) {
        this.cityName = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
