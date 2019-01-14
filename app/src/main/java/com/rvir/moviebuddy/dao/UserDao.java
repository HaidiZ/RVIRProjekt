package com.rvir.moviebuddy.dao;

import com.rvir.moviebuddy.entity.User;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface UserDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Completable insertUser(User user);

  @Query("SELECT COUNT(userName) FROM user")
  Single<Integer> getUsersCount();

}
