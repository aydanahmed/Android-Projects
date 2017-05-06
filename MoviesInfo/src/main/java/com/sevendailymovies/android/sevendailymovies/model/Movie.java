package com.sevendailymovies.android.sevendailymovies.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;

public class Movie {
    private String title;
    private String director;
    private String plot;
    private String posterURL;
    private String rate;
    private String weekend;
    private String gross;
    private String genre;
    private String stars;
    private Bitmap poster;
    private String videoLink;
    private String year;
    private String country;
    private boolean isLiked=false;
    private ArrayList<String> links;
    private static HashMap<String, ArrayList<Movie>> categories = new HashMap<>();

    public Movie(String title, String director, String plot, String stars, String genre, String rate, String gross, String weekend, String posterURL) {
        this.title = title;
        this.director = director;
        this.plot = plot;
        this.posterURL = posterURL;
        this.rate = rate;
        this.genre = genre;
        this.stars = stars;
        this.weekend = weekend;
        this.gross = gross;
    }

    public Movie(String title, String genre, String year, String rate, String country, String description, String casts, String director, String posterURL, String trailer) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rate = rate;
        this.stars = casts;
        this.director = director;
        this.posterURL = posterURL;
        this.videoLink = trailer;
        this.plot = description;
        this.country = country;
        links = new ArrayList<>();

    }

    public String getCountry() {
        return country;
    }

    public String getYear() {
        return year;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getRate() {
        return rate;
    }

    public String getWeekend() {
        return weekend;
    }

    public String getGross() {
        return gross;
    }

    public String getGenre() {
        return genre;
    }

    public String getStars() {
        return stars;
    }

    public void setPoster(Bitmap poster) {
        this.poster = poster;
    }

    public String getPlot() {
        return plot;
    }


    public Bitmap getPoster() {
        return poster;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }


    public String getPosterURL() {
        return posterURL;
    }


    public static void addMovieToCategory(Movie movie) {
        switch (movie.getGenre()) {
            case "Action":
                createCategoryAndAddMovie("Action", movie);
                break;

            case "Adventure":
                createCategoryAndAddMovie("Adventure", movie);
                break;

            case "Fantasy":
                createCategoryAndAddMovie("Fantasy", movie);
                break;

            case "History":
                createCategoryAndAddMovie("History", movie);
                break;

            case "Horror":
                createCategoryAndAddMovie("Horror", movie);
                break;

            case "Mystery":
                createCategoryAndAddMovie("Mystery", movie);
                break;

            case "Comedy":
                createCategoryAndAddMovie("Comedy", movie);
                break;

            case "Crime":
                createCategoryAndAddMovie("Crime", movie);
                break;

            case "Drama":
                createCategoryAndAddMovie("Drama", movie);
                break;

            case "Musical":
                createCategoryAndAddMovie("Musical", movie);
                break;

            case "Biography":
                createCategoryAndAddMovie("Biography", movie);
                break;

            case "Documentary":
                createCategoryAndAddMovie("Documentary", movie);
                break;

            case "Thriller":
                createCategoryAndAddMovie("Thriller", movie);
                break;
            case "Sci-Fi":
                createCategoryAndAddMovie("Sci-Fi", movie);
                break;
        }
    }

    private static void createCategoryAndAddMovie(String categoryName, Movie movie) {
        if (!categories.containsKey(categoryName)) {
            categories.put(categoryName, new ArrayList<Movie>());
        }
        for(int i=0;i<categories.get(categoryName).size();i++){
            if(categories.get(categoryName).get(i).getTitle().equals(movie.getTitle())){
                categories.get(categoryName).remove(i);
            }
        }
        categories.get(categoryName).add(movie);


    }

    public static ArrayList<Movie> getMoviesFromCategory(String categoryName) {
        for (String categoryN : categories.keySet()) {
            if (categoryName.equals(categoryN)) {
                return categories.get(categoryN);
            }
        }
        return null;
    }

    public void addMovieWatchLink(String link) {
        if (link != null && !link.equals("nolink")) {
            this.links.add(link);
        }
    }

    public boolean checkMovieWatchLinks() {

        return this.links.size() != 0;
    }

    public ArrayList<String> getMovieWatchlinks() {
        if (links.size() != 0) {
            return this.links;
        }
        return null;
    }

    public void likeMovie(){
        this.isLiked = true;
    }

    public void unLikeMovie(){
        this.isLiked = false;
    }


}
