package com.rvir.moviebuddy.location.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.rvir.moviebuddy.R;

import com.rvir.moviebuddy.location.entities.Result;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PlacesListAdapter extends ArrayAdapter<Result> {

    private Context context;
    private List<Result> results;

    public PlacesListAdapter(Context context, List<Result> results){
        super(context, R.layout.place_row_layout, results);
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
