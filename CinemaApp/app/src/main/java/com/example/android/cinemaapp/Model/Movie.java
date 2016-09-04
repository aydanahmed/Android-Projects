package com.example.android.cinemaapp.Model;

import java.io.Serializable;

/**
 * Created by Aydin on 4.9.2016 г..
 */
public class Movie implements Serializable {
    private int picture;
    private String title;
    private String workingTime;
    private int ticket;

    public Movie(int picture, String title, String workingTime, int ticket) {
        this.picture = picture;
        this.title = title;
        this.workingTime = workingTime;
        this.ticket = ticket;
    }

    public int getTicket() {
        return ticket;
    }

    public int getPicture() {
        return picture;
    }

    public String getTitle() {
        return title;
    }

    public String getWorkingTime() {
        return workingTime;
    }
}
