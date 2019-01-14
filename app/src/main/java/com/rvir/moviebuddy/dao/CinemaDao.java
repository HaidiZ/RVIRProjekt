package com.rvir.moviebuddy.dao;

import com.rvir.moviebuddy.entity.Cinema;
import com.rvir.moviebuddy.entity.User;

import java.util.ArrayList;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface CinemaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertCinema(Cinema cinema);

    @Query("SELECT * FROM cinema")
    ArrayList<Cinema> getAllCinemas();

}
