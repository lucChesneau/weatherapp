package com.luc.android.cdafirstapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.luc.android.cdafirstapp.R;
import com.luc.android.cdafirstapp.models.City;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<City> mCities;


    // Constructor
    public CityAdapter(Context context, ArrayList<City> cities){
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
        City city = mCities.get(position);
        holder.mCityName.setText(city.getmName());
        holder.mWeatherIcon.setImageResource(city.getmWeatherIcon());
        holder.mCity = city;
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    // Classe holder qui contient la vue d’un item
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        public TextView mCityName;

        public ImageView mWeatherIcon;

        public City mCity;


        public ViewHolder(View view) {
            super(view);
            mCityName = view.findViewById(R.id.text_view_city_name);
            mWeatherIcon = view.findViewById(R.id.image_view_city_icon);
            view.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Supprimer la ville");
            builder.setMessage("Voulez-vous vraiment supprimer cette ville ?");
            builder.setPositiveButton("Oui", (dialog, i) -> {
                mCities.remove(mCity);
                notifyDataSetChanged();
            });
            builder.setNegativeButton("Non", (dialog, i) -> dialog.cancel());
            builder.create().show();
            return false;
        }

    }
}

