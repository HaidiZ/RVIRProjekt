package com.rvir.moviebuddy.location.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Result implements Serializable {

    @SerializedName("geometry")
    private Geometry geometry;

    @SerializedName("icon")
    private String icon;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("opening_hours")
    private OpeningHours openingHours;

    @SerializedName("photos")
    private List<Photo> photos = new ArrayList<>();

    @SerializedName("place_id")
    private String placeId;


}
