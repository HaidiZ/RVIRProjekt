package com.rvir.moviebuddy.api;

import com.rvir.moviebuddy.api.dto.movie.Movie;
import com.rvir.moviebuddy.api.dto.movielist.MovieResults;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDbService {

  @GET("discover/movie")
  Single<MovieResults> getPopularMovies(@Query("page") Integer page, @Query("sort_by") String sortBy);


  @GET("movie/{movieId}")
  Single<Movie> getMovie(@Path("movieId") Integer movieId);

}
