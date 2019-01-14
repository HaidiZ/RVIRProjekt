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
import com.rvir.moviebuddy.api.dto.movielist.Result;
import com.rvir.moviebuddy.view.moviedetails.MovieDetailsActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

  private Context context;
  private Activity activity;
  private List<Result> movieResults;

  public MoviesAdapter(Context context, Activity activity) {
    this.context = context;
    this.activity = activity;
  }

  @NonNull
  @Override
  public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.movie_list_row, parent, false);
    return new MovieViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
    Result currentMovieResults = movieResults.get(position);
    holder.movieTitle.setText(currentMovieResults.getTitle());
  }

  @Override
  public int getItemCount() {
    if (movieResults == null) {
      return 0;
    } else {
      return movieResults.size();
    }
  }

  public void setMovies(List<Result> movieResults) {
    this.movieResults = movieResults;
    notifyDataSetChanged();
  }

  public class MovieViewHolder extends RecyclerView.ViewHolder {
    private TextView movieTitle;

    public MovieViewHolder(@NonNull View itemView) {
      super(itemView);
      this.movieTitle = itemView.findViewById(R.id.movieTitle);

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Log.d("ADAPTER", "Prikaži podrobnosti");
          Integer movieId = movieResults.get(getAdapterPosition()).getId();
          Intent intent = new Intent(activity, MovieDetailsActivity.class);
          intent.putExtra("id", movieId);
          activity.startActivity(intent);
        }
      });
    }
  }
}
