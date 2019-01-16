package com.rvir.moviebuddy.entity;

import com.rvir.moviebuddy.dao.CinemaDao;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import io.reactivex.Completable;

@Entity
public class Cinema {
    @PrimaryKey
    @NonNull
    private String nazivKina;
    private String naslov;
    private double dolzina;
    private double sirina;

    public Cinema(String naziv, String naslov, double dolzina, double sirina) {

    }

    @NonNull
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

    public double getDolzina() {
        return dolzina;
    }

    public void setDolzina(double koordinataX) {
        this.dolzina = koordinataX;
    }

    public double getSirina() {
        return sirina;
    }

    public void setSirina(double koordinataY) {
        this.sirina = koordinataY;
    }

  /*  public static void main(String[] args) {
        Cinema cinema1 = new Cinema("CineplexxMurska","Stefana Kovača 43, 9000 Murska Sobota", 46.660544, 16.145138);
        CinemaDao cinemaDao = new CinemaDao() {
            @Override
            public Completable insertCinema(Cinema cinema) {
                return null;
            }

            @Override
            public ArrayList<Cinema> getAllCinemas() {
                return null;
            }
        };
        cinemaDao.insertCinema(cinema1);
        //System.out.print(cinemaDao.getAllCinemas());
    }*/
}
