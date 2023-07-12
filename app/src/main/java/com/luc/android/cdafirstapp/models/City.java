package com.luc.android.cdafirstapp.models;

import org.json.JSONException;
import org.json.JSONObject;

public class City {

    private String mName;

    private String mDescription;

    private String mTemperature;

    private int mWeatherIcon;

    private int mIdCity;

    private  int mLongitude;

    private int mLatitude;


    public City(String name, String description, String temperature, int weatherIcon) {
        mName = name;
        mDescription = description;
        mTemperature = temperature;
        mWeatherIcon = weatherIcon;
    }

    public City(String stringJson) throws JSONException {
        JSONObject json = new JSONObject(stringJson);
        mName = json.getString("name");
        mDescription = json.getJSONArray("weather").getJSONObject(0).getString("description");
        mTemperature = json.getJSONObject("main").getString("temp");
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmTemperature() {
        return mTemperature;
    }

    public void setmTemperature(String mTemperature) {
        this.mTemperature = mTemperature;
    }

    public int getmWeatherIcon() {
        return mWeatherIcon;
    }

    public void setmWeatherIcon(int mWeatherIcon) {
        this.mWeatherIcon = mWeatherIcon;
    }
}
