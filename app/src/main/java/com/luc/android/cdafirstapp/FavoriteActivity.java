package com.luc.android.cdafirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FavoriteActivity extends AppCompatActivity {


    Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        Bundle extras = getIntent().getExtras();
        String strMessage = extras.getString("data_city_name");

        TextView textData = findViewById(R.id.text_data);
        textData.setText(strMessage);

        //String value = intent.getStringExtra("keyTest");
    }
}