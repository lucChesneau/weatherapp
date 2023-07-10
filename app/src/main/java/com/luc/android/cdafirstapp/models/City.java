package com.luc.android.cdafirstapp.models;

public class City {

    private String mName;

    private String mDescription;

    private String mTemperature;

    private int mWeatherIcon;

    public City(String name, String description, String temperature, int weatherIcon) {
        mName = name;
        mDescription = description;
        mTemperature = temperature;
        mWeatherIcon = weatherIcon;
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
