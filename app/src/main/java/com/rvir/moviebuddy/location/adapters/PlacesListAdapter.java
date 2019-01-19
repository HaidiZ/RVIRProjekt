package com.rvir.moviebuddy.location.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import com.rvir.moviebuddy.R;

import com.rvir.moviebuddy.location.entities.Result;

import java.util.List;

public class PlacesListAdapter extends ArrayAdapter<Result> {

    private Context context;
    private List<Result> results;

    public PlacesListAdapter(Context context, List<Result> results){
        super(context, R.layout.place_row_layout);
    }
}
