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

    public FavouriteAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }


    @NonNull
    @Override
    public FavouriteAdapter.FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favourite_list_row, parent, false);
        return new FavouriteAdapter.FavouriteViewHolder(view);
    }


    @Override
    @NonNull
    public void onBindViewHolder( FavouriteAdapter.FavouriteViewHolder holder, int position) {
        Favourite currentFav = seznamIzbranih.get(position);
        holder.nazivFilma.setText(currentFav.getNazivFilma());
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
            this.nazivFilma = itemView.findViewById(R.id.runtimeLabel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ADAPTER", "Prikaži podrobnosti");
                    String nazivFilma = seznamIzbranih.get(getAdapterPosition()).getNazivFilma();
                    Intent intent = new Intent(activity, MovieDetailsActivity.class);
                    intent.putExtra("id", nazivFilma);
                    activity.startActivity(intent);
                }
            });
        }
    }
}
