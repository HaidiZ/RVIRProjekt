package com.rvir.moviebuddy.location.services;

import com.rvir.moviebuddy.location.entities.PlacesResults;
import com.rvir.moviebuddy.location.entities.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleMapAPI {

    @GET("place/nearbysearch/json")
    Call<PlacesResults> getNearBy(
        @Query("location") String location,
        @Query("radius") int radius,
        @Query("type") String type,
        @Query("keyword") String keyword,
        @Query("key") String key
    );
}
