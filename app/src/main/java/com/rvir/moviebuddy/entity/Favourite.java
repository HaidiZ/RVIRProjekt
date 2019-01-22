package com.rvir.moviebuddy.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Favourite {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private  int id;
    private String nazivFilma;

    public Favourite(String nazivFilma) {
        this.nazivFilma = nazivFilma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivFilma() {
        return nazivFilma;
    }

    public void setNazivFilma(String nazivFilma) {
        this.nazivFilma = nazivFilma;
    }
}
