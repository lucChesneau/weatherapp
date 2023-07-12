package com.luc.android.cdafirstapp.network;
import com.luc.android.cdafirstapp.models.weather_api.CityRetrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("weather")
    Call<CityRetrofit> getResponse(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("appid") String appid,
            @Query("units") String units,
            @Query("lang") String lang
    );

    @GET("weather")
    Call<CityRetrofit> getResponseByCityName(
            @Query("q") String q,
            @Query("lang") String lang,
            @Query("units") String units,
            @Query("appid") String appid
    );

}
