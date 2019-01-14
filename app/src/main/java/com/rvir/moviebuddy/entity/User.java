package com.rvir.moviebuddy.entity;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

  @PrimaryKey
  @NonNull
  private String userName;

  private Integer userTypeEnum;

  private Date birthDate;

  public String getUserName() {
    return userName;
  }

  public void setUserName(@NonNull String userName) {
    this.userName = userName;
  }

  public UserType getUserType() {
    if (userTypeEnum == null) {
      return null;
    }
    return UserType.values()[this.userTypeEnum];
  }

  public void setUserType(UserType userType) {
    this.userTypeEnum = userType.ordinal();
  }

  public Integer getUserTypeEnum() {
    return userTypeEnum;
  }

  public void setUserTypeEnum(Integer userTypeEnum) {
    this.userTypeEnum = userTypeEnum;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }
}
