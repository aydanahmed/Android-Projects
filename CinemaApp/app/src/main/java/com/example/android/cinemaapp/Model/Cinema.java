package com.example.android.cinemaapp.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Aydin on 4.9.2016 г..
 */
public class Cinema implements Serializable{
    private String name;
    private ArrayList<Integer> pictures;
    private ArrayList<Movie> movies;
    private String address;
    private String workingTime;
    private String telephone;
    private int parkingPlace;

    public Cinema(String name, String address, String workingTime, String telephone, int parkingPlace) {
        this.name = name;
        this.address = address;
        this.workingTime = workingTime;
        this.telephone = telephone;
        this.parkingPlace = parkingPlace;
        pictures = new ArrayList<>();
        movies = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getPictures() {
        return pictures;
    }

    public int getParkingPlace() {
        return parkingPlace;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public String getTelephone() {
        return telephone;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
    public void addMovie(Movie e){
        movies.add(e);

    }

    public void addPictures( int picture){
       this.getPictures().add(picture);

    }
}
