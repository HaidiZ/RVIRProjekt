package com.rvir.moviebuddy.entity;

public enum UserType {

  STUDENT("Student"), MINOR("Minor"), PENSIONER("Pensioner");

  private String value;

  UserType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static UserType getEnum(String value) {
    for (UserType v : values())
      if (v.getValue().equalsIgnoreCase(value)) return v;
    throw new IllegalArgumentException();
  }
}
