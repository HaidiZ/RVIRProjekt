package com.rvir.moviebuddy.dao;

import com.rvir.moviebuddy.entity.Favourite;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;

@Dao
public interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertFavourite(Favourite favourite);

    @Query("SELECT * FROM Favourite")
    List<Favourite> getAll();


}
