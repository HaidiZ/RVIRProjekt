package com.rvir.moviebuddy;

import android.app.Application;

import com.rvir.moviebuddy.api.ApiServiceBuilder;
import com.rvir.moviebuddy.api.MovieDbService;

public class MovieBuddyApplication extends Application {

  private static MovieDbService movieApi;

  @Override
  public void onCreate() {
    super.onCreate();
    // Required initialization logic here!
    movieApi = ApiServiceBuilder.build();
  }

  public static MovieDbService getMovieApi() {
    return movieApi;
  }

}
