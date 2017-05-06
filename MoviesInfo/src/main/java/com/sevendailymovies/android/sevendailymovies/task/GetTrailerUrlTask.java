package com.sevendailymovies.android.sevendailymovies.task;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

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

public class GetTrailerUrlTask extends AsyncTask<String,Void,String> {
    private ArrayList<Movie> movies;


    public GetTrailerUrlTask(ArrayList<Movie> movies){
        this.movies=movies;
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
        JSONObject jsonObj;
        try {
            jsonObj = new JSONObject(json);
            JSONArray videosArr = jsonObj.getJSONArray("trailers");
            for(int i = 0 ; i < videosArr.length();i++) {
                JSONObject c = videosArr.getJSONObject(i);
                movies.get(i).setVideoLink(c.getString(Integer.toString(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
