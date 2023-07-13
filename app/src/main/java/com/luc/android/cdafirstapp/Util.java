package com.luc.android.cdafirstapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.luc.android.cdafirstapp.models.weather_api.CityRetrofit;

import org.json.JSONArray;

import java.util.ArrayList;

public abstract class Util {
    public static final String PREFS_NAME = "city_name";;
    public static final String PREFS_FAVORITE_CITIES = "cities_favorites";

    public static void saveFavouriteCities(Context context, ArrayList<CityRetrofit> cities) {
        JSONArray jsonArrayCities = new JSONArray();
        for (int i = 0; i < cities.size(); i++) {
            jsonArrayCities.put(cities.get(i).getName());
        }
        SharedPreferences preferences = context.getSharedPreferences(Util.PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Util.PREFS_FAVORITE_CITIES, jsonArrayCities.toString());
        editor.apply();
    }

    public static ArrayList<CityRetrofit> initFavoriteCities(Context context) {
        ArrayList<CityRetrofit> cities = new ArrayList<>();
        return cities;
    }
}
