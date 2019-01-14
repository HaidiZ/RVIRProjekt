package com.rvir.moviebuddy.entity;

public enum MoviesSortType {

  POPULARITY("Popularnost", "popularity.desc"),
  RELEASE_DATE("Datum izdaje", "release_date.desc"),
  REVENUE("Prihodek", "revenue.desc"),
  RATING("Število glasov", "vote_count.desc");

  private String value;
  private String name;

  MoviesSortType(String name, String value) {
    this.value = value;
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public String getName() {
    return name;
  }
}
