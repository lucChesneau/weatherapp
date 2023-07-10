package com.luc.android.cdafirstapp.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.luc.android.cdafirstapp.R;
import com.luc.android.cdafirstapp.adapters.CityAdapter;
import com.luc.android.cdafirstapp.databinding.ActivityScrollingBinding;
import com.luc.android.cdafirstapp.models.City;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {

    private ActivityScrollingBinding binding;
    private ArrayList<City> mCities;
    private RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mRecyclerView = findViewById(R.id.cities_recycler_view);

        mCities = new ArrayList<>();
        City city1 = new City("Montréal", "Légères pluies", "22°C", R.drawable.weather_rainy_grey);
        City city2 = new City("New York", "Ensoleillé", "22°C", R.drawable.weather_sunny_grey);
        City city3 = new City("Paris", "Nuageux", "24°C", R.drawable.weather_foggy_grey);
        City city4 = new City("Toulouse", "Pluies modérées", "20°C", R.drawable.weather_rainy_grey);

        for(int i = 0; i < 1; i++) {
            mCities.add(city1);
            mCities.add(city2);
            mCities.add(city3);
            mCities.add(city4);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        CityAdapter mAdapter = new CityAdapter(this, mCities);
        mRecyclerView.setAdapter(mAdapter);


        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ScrollingActivity.this);
                builder.setTitle("Ajouter une ville");

                View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_add_favorite, null);
                final EditText editTextCity = v.findViewById(R.id.edit_text_dialog_city);
                builder.setView(v);

                builder.setNegativeButton("Annuler", (dialogInterface, i) -> dialogInterface.cancel());
                builder.setPositiveButton("OK", (dialogInterface, i) ->{
                    mCities.add(new City(editTextCity.getText().toString(), "Nuageux", "24°C", R.drawable.weather_foggy_grey));
                    mAdapter.notifyDataSetChanged();
                    System.out.println("OK");
                });


                builder.create().show();

            }
        });
    }
}