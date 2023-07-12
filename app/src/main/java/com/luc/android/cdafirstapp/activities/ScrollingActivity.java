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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.luc.android.cdafirstapp.R;
import com.luc.android.cdafirstapp.adapters.CityAdapter;
import com.luc.android.cdafirstapp.databinding.ActivityScrollingBinding;
import com.luc.android.cdafirstapp.models.City;
import com.luc.android.cdafirstapp.models.weather_api.CityRetrofit;
import com.luc.android.cdafirstapp.network.ApiService;
import com.luc.android.cdafirstapp.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;

public class ScrollingActivity extends AppCompatActivity {

    private ActivityScrollingBinding binding;
    private ArrayList<CityRetrofit> mCities;
    private RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCities = new ArrayList<CityRetrofit>();

        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mRecyclerView = findViewById(R.id.cities_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        CityAdapter mAdapter = new CityAdapter(this, mCities);
        mRecyclerView.setAdapter(mAdapter);


        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        new RetrofitClient();
        ApiService apiService = RetrofitClient.getInstance().getApi();



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

                    Call<CityRetrofit> call = apiService.getResponseByCityName(editTextCity.getText().toString(), "fr", "metric","01897e497239c8aff78d9b8538fb24ea");

                    call.enqueue(new retrofit2.Callback<CityRetrofit>() {
                        @Override
                        public void onResponse(Call<CityRetrofit> call, retrofit2.Response<CityRetrofit> response) {
                            if (response.isSuccessful()) {
                                CityRetrofit cityRetrofit = response.body();

                                mCities.add(cityRetrofit);

                                Toast.makeText(ScrollingActivity.this, "Data received!", Toast.LENGTH_SHORT).show();
                                mAdapter.notifyDataSetChanged();

                            } else {
                                Toast.makeText(ScrollingActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<CityRetrofit> call, Throwable t) {
                            Log.d("testdefou", "Erreur lors de l'envoi de la requête : " + t.getMessage());
                            t.printStackTrace();
                        }
                    });
                });

                builder.create().show();

            }
        });
    }
}