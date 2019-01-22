package com.rvir.moviebuddy.view.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rvir.moviebuddy.R;
import com.rvir.moviebuddy.entity.Favourite;
import com.rvir.moviebuddy.view.moviedetails.MovieDetailsActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>{
    private Context context;
    private Activity activity;
    private List<Favourite> seznamIzbranih;

    public FavouriteAdapter(List<Favourite> seznamIzbranih) {
        this.seznamIzbranih = seznamIzbranih;
    }


    @NonNull
    @Override
    public FavouriteAdapter.FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View vrsticaSeznama = inflater.inflate(R.layout.favourite_list_row, parent, false);
        FavouriteViewHolder viewHolder = new FavouriteViewHolder(vrsticaSeznama);
        return viewHolder;
    }


    @Override
    @NonNull
    public void onBindViewHolder( FavouriteAdapter.FavouriteViewHolder holder, int position) {
        Favourite favourite = seznamIzbranih.get(position);
        holder.nazivFilma.setText(favourite.getNazivFilma());
    }

    @Override
    public int getItemCount() {
        if (seznamIzbranih == null) {
            return 0;
        } else {
            return seznamIzbranih.size();
        }
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder {
        private TextView nazivFilma;

        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nazivFilma = itemView.findViewById(R.id.textView);
        }
    }
    public void setData(List<Favourite> newData) {
        this.seznamIzbranih = newData;
        notifyDataSetChanged();
    }
}
