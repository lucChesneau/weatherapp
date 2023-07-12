package com.luc.android.cdafirstapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luc.android.cdafirstapp.R;
import com.luc.android.cdafirstapp.models.City;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    private TextView myTextViewCityName;
    private RelativeLayout noInternetLayout;
    private OkHttpClient client;

    private City mCurrentCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        client = new OkHttpClient();

        // Test de connexion internet
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isConnected = testInternetConnexion(connMgr);

        setContentView(R.layout.activity_main);
        noInternetLayout = findViewById(R.id.no_internet_layout);
        if (!isConnected) {
            noInternetLayout.setVisibility(View.VISIBLE);
        } else {
            Request request = new Request.Builder()
                    .url("https://api.openweathermap.org/data/2.5/weather?lat=47.390026&lon=0.688891&appid=01897e497239c8aff78d9b8538fb24ea&units=metric&lang=fr")
                    .build();

            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(okhttp3.Call call, java.io.IOException e) {
                    Log.d("testdefou", "Erreur lors de l'envoi de la requête : " + e.getMessage());
                    e.printStackTrace();
                }

                @Override
                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws java.io.IOException {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        final String myResponse = response.body().string();
                        Log.d("testdefou", myResponse);

                        runOnUiThread(new Runnable() {
                            public void run() {

                                try {
                                    JSONObject json = new JSONObject(myResponse);
                                    mCurrentCity = new City(json.toString());
                                    updateUi(mCurrentCity);

                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });


                    }
                }
            });
        }


        myTextViewCityName = findViewById(R.id.text_view_city_name);
        myTextViewCityName.setText(R.string.city_name);

        Button buttonOpenActivity = findViewById(R.id.button_open_activity);

        buttonOpenActivity.setOnClickListener(view -> startScrollingActivity());

        /*
        buttonOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFavoriteActivity();
            }
        });*/

    }

    private void startScrollingActivity() {
        Intent intent = new Intent(MainActivity.this, ScrollingActivity.class);
        EditText editTextCityName = findViewById(R.id.edit_text_city_name);
        intent.putExtra("data_city_name", editTextCityName.getText().toString());
        startActivity(intent);
    }

    private boolean testInternetConnexion(ConnectivityManager connMgr) {
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(this, "Connexion internet OK", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Pas de connexion internet", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void updateUi(City myCity) throws JSONException {

        String name = myCity.getmName();
        String temp = myCity.getmTemperature();
        String description = myCity.getmDescription();

        myTextViewCityName.setText(name);
        TextView textViewTemperature = findViewById(R.id.text_view_city_temp);
        textViewTemperature.setText(temp);

        TextView textViewDescription = findViewById(R.id.text_view_city_description);
        textViewDescription.setText(description);
    }

}