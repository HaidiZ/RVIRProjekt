package com.rvir.moviebuddy.location.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Photo implements Serializable {

    @SerializedName("height")
    private Integer height;

    @SerializedName("width")
    private Integer width;

    @SerializedName("html_attributions")
    private List<String> htmlattributions = new ArrayList<>();

    @SerializedName("photo_reference")
    private String photoReference;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public List<String> getHtmlattributions() {
        return htmlattributions;
    }

    public void setHtmlattributions(List<String> htmlattributions) {
        this.htmlattributions = htmlattributions;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }
}
