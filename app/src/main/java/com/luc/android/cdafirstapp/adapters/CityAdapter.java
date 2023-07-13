package com.luc.android.cdafirstapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.luc.android.cdafirstapp.R;
import com.luc.android.cdafirstapp.Util;
import com.luc.android.cdafirstapp.models.City;
import com.luc.android.cdafirstapp.models.weather_api.CityRetrofit;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<CityRetrofit> mCities;


    // Constructor
    public CityAdapter(Context context, ArrayList<CityRetrofit> cities){
        mContext = context;
        mCities = cities;
    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_favorite_city, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder holder, int position) {
        CityRetrofit city = mCities.get(position);
        holder.mCityName.setText(city.getName());
        Picasso.with(mContext).load("https://openweathermap.org/img/w/" + city.getWeather().get(0).getIcon() + ".png").placeholder(R.drawable.weather_clear_night_grey).into(holder.mWeatherIcon);
        Log.d("loglog", "onBindViewHolder: " + city.getWeather().get(0).getIcon());
        holder.mCityDescription.setText(city.getWeather().get(0).getDescription());
        holder.mCityTemperature.setText(city.getMain().getTemp().toString() + "°C");
        holder.mCity = city;
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    // Classe holder qui contient la vue d’un item
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        public TextView mCityName;

        public TextView mCityTemperature;

        public TextView mCityDescription;

        public ImageView mWeatherIcon;

        public CityRetrofit mCity;


        public ViewHolder(View view) {
            super(view);
            mCityName = view.findViewById(R.id.text_view_city_name);
            mWeatherIcon = view.findViewById(R.id.image_view_city_icon);
            mCityDescription = view.findViewById(R.id.text_view_city_desc);
            mCityTemperature = view.findViewById(R.id.text_view_city_temp);
            view.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Supprimer la ville");
            builder.setMessage("Voulez-vous vraiment supprimer cette ville ?");
            builder.setPositiveButton("Oui", (dialog, i) -> {
                mCities.remove(mCity);
                Util.saveFavouriteCities(this.itemView.getContext(), mCities);
                notifyDataSetChanged();
            });
            builder.setNegativeButton("Non", (dialog, i) -> dialog.cancel());
            builder.create().show();
            return false;
        }

    }
}

