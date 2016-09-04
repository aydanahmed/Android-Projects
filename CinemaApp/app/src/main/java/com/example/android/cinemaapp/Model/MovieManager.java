package com.example.android.cinemaapp.Model;

/**
 * Created by Aydin on 4.9.2016 г..
 */
public class MovieManager {
    private static MovieManager ourInstance = new MovieManager();

    public static MovieManager getInstance() {
        return ourInstance;
    }

    private MovieManager() {
    }

    public Movie createMovie(int picture, String title, String workingTime, int ticket){
        Movie movie = new Movie(picture,title,workingTime,ticket);
        return movie;

    }



}
