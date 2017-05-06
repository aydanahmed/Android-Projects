package com.sevendailymovies.android.sevendailymovies.task;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.ProgressBar;

import com.sevendailymovies.android.sevendailymovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class GetMovieInfoTask extends AsyncTask<String, Void, String> {
    private ArrayList<Movie> movies;
    private RecyclerView.Adapter adapter;
    private ProgressBar progressBar;


    public GetMovieInfoTask(ArrayList<Movie> movies, RecyclerView.Adapter adapter, ProgressBar progressBar) {
        this.movies = movies;
        this.adapter = adapter;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... params) {
        String address = decodeString(params[0]);
        String json = "";
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            Scanner sc = new Scanner(connection.getInputStream());
            while (sc.hasNextLine()) {
                json += (sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }


    @Override
    protected void onPostExecute(String json) {
        progressBar.setVisibility(View.GONE);
        JSONObject jsonObj;
        try {
            jsonObj = new JSONObject(json);
            JSONArray moviesArr = jsonObj.getJSONArray("movies");
            for (int i = 0; i < moviesArr.length(); i++) {
                JSONObject c = moviesArr.getJSONObject(i);
                Movie movie = new Movie(c.getString("Title"), c.getString("Genre"), c.getString("Year"), c.getString("IMDB Rating"), c.getString("Country"), c.getString("Description"), c.getString("Casts"), c.getString("Director"), c.getString("Poster"), c.getString("Trailer"));
                String link1 = c.getString("Link1");
                String link2 = c.getString("Link2");
                String link3 = c.getString("Link3");
                if (!link1.isEmpty()) {
                    movie.addMovieWatchLink(link1);
                }

                if (!link2.isEmpty()) {
                    movie.addMovieWatchLink(link2);
                }

                if (!link3.isEmpty()) {
                    movie.addMovieWatchLink(link3);
                }
                checkMoviesList(movies, movie);

                Movie.addMovieToCategory(movie);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void checkMoviesList(ArrayList<Movie> moviesMov, Movie movie) {
        if (moviesMov.size() != 0) {
            for (int i = 0; i < moviesMov.size(); i++) {
                if (moviesMov.get(i).getTitle().equals(movie.getTitle())) {
                    moviesMov.remove(i);
                    adapter.notifyDataSetChanged();
                }
            }
        }
        moviesMov.add(movie);
    }

    private String decodeString(String encoded) {
        byte[] dataDec = Base64.decode(encoded, Base64.DEFAULT);
        String decodedString = "";
        try {

            decodedString = new String(dataDec, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        } finally {

            return decodedString;
        }
    }

}
