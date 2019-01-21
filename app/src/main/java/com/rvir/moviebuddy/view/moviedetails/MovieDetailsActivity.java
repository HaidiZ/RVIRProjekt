package com.rvir.moviebuddy.view.moviedetails;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.rvir.moviebuddy.MovieBuddyApplication;
import com.rvir.moviebuddy.R;
import com.rvir.moviebuddy.api.ApiConstants;
import com.rvir.moviebuddy.api.dto.movie.Genre;
import com.rvir.moviebuddy.api.dto.movie.Movie;
import com.rvir.moviebuddy.dao.AppDatabase;
import com.rvir.moviebuddy.entity.Favourite;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

public class MovieDetailsActivity extends AppCompatActivity {
  AppDatabase mydb;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_details);
    Integer movieId = getIntent().getExtras().getInt("id");
    Log.d("MOVIE_ID", movieId + "");
    loadMovie(movieId);
    Button addButton = findViewById(R.id.buttonAdd);
    addButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        addToFavouriteList();
      }
    });
  }

  private void loadMovie(Integer id) {
    MovieBuddyApplication.getMovieApi()
        .getMovie(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new DisposableSingleObserver<Movie>() {
          @Override
          public void onSuccess(Movie movie) {
            setMovieValues(movie);
          }

          @Override
          public void onError(Throwable e) {
            Log.e("ERROR", e.getMessage(), e);
          }
        });
  }

  private void setMovieValues(Movie movie) {
    TextView titleText = findViewById(R.id.titleValue);
    titleText.setText(movie.getTitle());
    TextView taglineText = findViewById(R.id.taglineValue);
    taglineText.setText(movie.getTagline());
    TextView overviewText = findViewById(R.id.overviewValue);
    overviewText.setText(movie.getOverview());
    TextView genresText = findViewById(R.id.genresValue);
    StringBuilder genresString = new StringBuilder();
    for (Genre genre : movie.getGenres()) {
      genresString.append(genre.getName()).append(System.getProperty("line.separator"));
    }
    genresText.setText(genresString);

    MaterialRatingBar ratingBar = findViewById(R.id.ratingBar);
    double d = movie.getVoteAverage();
    float f = (float) d;
    ratingBar.setRating(f);

    ImageView imagePoster = findViewById(R.id.imagePoster);
    Picasso.get().load(ApiConstants.IMAGE_URL + movie.getPosterPath()).into(imagePoster);

    TextView revenueText = findViewById(R.id.revenueValue);
    TextView revenueLabel = findViewById(R.id.revenueLabel);
    if (movie.getRevenue() > 0) {
      revenueText.setVisibility(View.VISIBLE);
      revenueLabel.setVisibility(View.VISIBLE);
      NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
      String revenueValue = format.format(movie.getRevenue());
      revenueText.setText(revenueValue);
    } else {
      revenueText.setVisibility(View.GONE);
      revenueLabel.setVisibility(View.GONE);
    }

    TextView runtimeText = findViewById(R.id.runtimeValue);
    TextView runtimeLabel = findViewById(R.id.runtimeLabel);
    if (movie.getRuntime() > 0) {
      runtimeText.setVisibility(View.VISIBLE);
      runtimeLabel.setVisibility(View.VISIBLE);
      String runtimeValue = movie.getRuntime() + " min";
      runtimeText.setText(runtimeValue);
    } else {
      runtimeText.setVisibility(View.GONE);
      runtimeLabel.setVisibility(View.GONE);
    }
  }

  public void addToFavouriteList() {
    final Favourite favourite;
    TextView runtimeLabel = findViewById(R.id.runtimeLabel);
    String naslov = runtimeLabel.getText().toString();
    favourite = new Favourite(naslov);
    AsyncTask.execute(new Runnable() {
      @Override
      public void run() {
        mydb.favouriteDao().insertFavourite(favourite);
      }
    });
  }
}
