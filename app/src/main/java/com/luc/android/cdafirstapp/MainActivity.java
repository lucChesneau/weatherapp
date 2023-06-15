package com.luc.android.cdafirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView myTextViewCityName;

    private RelativeLayout noInternetLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Test de connexion internet
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isConnected = testInternetConnexion(connMgr);

        setContentView(R.layout.activity_main);
        noInternetLayout = findViewById(R.id.no_internet_layout);
        if (!isConnected) {
            noInternetLayout.setVisibility(View.VISIBLE);
        }


        myTextViewCityName = findViewById(R.id.text_view_city_name);
        myTextViewCityName.setText(R.string.city_name);

        Toast.makeText(this, myTextViewCityName.getText().toString(), Toast.LENGTH_SHORT).show();

        Button buttonOpenActivity = findViewById(R.id.button_open_activity);

        buttonOpenActivity.setOnClickListener(view -> startFavoriteActivity());

        /*
        buttonOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFavoriteActivity();
            }
        });*/



    }


    private void startFavoriteActivity() {
        Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
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

}