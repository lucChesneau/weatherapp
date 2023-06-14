package com.luc.android.cdafirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonOpenActivity = findViewById(R.id.button_open_activity);

        buttonOpenActivity.setOnClickListener(
                v -> {
                    Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
                    intent.putExtra("keyTest", "valueTest");
                    startActivity(intent);
                });
    }


}