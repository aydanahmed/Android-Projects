package com.example.android.cinemaapp.Model;

import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Aydin on 4.9.2016 г..
 */
public class CinemaManager {
    private static CinemaManager ourInstance = new CinemaManager();
    private static HashMap<String,Cinema> cinemas;

    public static CinemaManager getInstance() {
        return ourInstance;
    }

    private CinemaManager() {
        cinemas = new HashMap<>();
    }


    public void createCinema(String name,  String address,String workingTime, String telephone, int parkingPlace){
        Cinema cinema = new Cinema(name,address,workingTime,telephone,parkingPlace);
        cinemas.put(name,cinema);

    }


    public Cinema getCinema(String name){
        if(!cinemas.containsKey(name)){
            return null;
        }
        return cinemas.get(name);


    }








}
