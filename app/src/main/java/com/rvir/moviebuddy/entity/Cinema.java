package com.rvir.moviebuddy.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cinema {
    @PrimaryKey
    @NonNull
    private int IDkino;
    private String nazivKina;
    private String naslov;
    private double koordinataX;
    private double koordinataY;

    @NonNull
    public int getID() {
        return IDkino;
    }

    public void setID(@NonNull int ID) {
        this.IDkino= ID;
    }

    public String getNazivKina() {
        return nazivKina;
    }

    public void setNazivKina(@NonNull String nazivKina) {
        this.nazivKina = nazivKina;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public double getKoordinataX() {
        return koordinataX;
    }

    public void setKoordinataX(double koordinataX) {
        this.koordinataX = koordinataX;
    }

    public double getKoordinataY() {
        return koordinataY;
    }

    public void setKoordinataY(double koordinataY) {
        this.koordinataY = koordinataY;
    }
}
