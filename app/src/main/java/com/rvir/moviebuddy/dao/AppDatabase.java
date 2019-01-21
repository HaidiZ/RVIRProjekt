package com.rvir.moviebuddy.dao;

import com.rvir.moviebuddy.entity.Cinema;
import com.rvir.moviebuddy.entity.Favourite;
import com.rvir.moviebuddy.entity.User;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class, Cinema.class, Favourite.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
  public abstract UserDao userDao();
  public abstract CinemaDao cinemaDao();
  public abstract FavouriteDao favouriteDao();
}
