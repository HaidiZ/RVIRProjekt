package com.rvir.moviebuddy.dao;

import android.content.Context;

import androidx.room.Room;

public class DbUtil {
  public static AppDatabase getDb(Context context) {
    return Room.databaseBuilder(context, AppDatabase.class, "movie-buddy-db").build();
  }
}
