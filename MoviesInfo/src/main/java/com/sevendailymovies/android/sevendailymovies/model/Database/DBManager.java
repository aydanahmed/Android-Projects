package com.sevendailymovies.android.sevendailymovies.model.Database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sevendailymovies.android.sevendailymovies.model.Movie;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    private static DBManager ourInstance;
    private static int version = 1;
    private Activity activity;
    private ArrayList<Movie> favouriteMovies = new ArrayList<>();

    public static DBManager getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new DBManager(context);
        }
        return ourInstance;
    }

    private DBManager(Context context) {
        super(context, "MoviesAppDB", null, version);
        loadMovies();
        this.activity = (Activity) context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TABLE_FAVOURITES (FAVOURITES_TITLE text PRIMARY KEY,FAVOURITES_GENRE text, FAVOURITES_YEAR text, FAVOURITES_RATE text, FAVOURITES_COUNTRY text, FAVOURITES_DESCRIPTION text,FAVOURITES_CASTS text, FAVOURITES_DIRECTOR text,FAVOURITES_POSTER_URL text,FAVOURITES_TRAILER text)");
        db.execSQL("CREATE TABLE TABLE_LINKS (LINKS_ID INTEGER PRIMARY KEY AUTOINCREMENT,LINKS_LINK1 text,LINKS_LINK2 text,LINKS_LINK3 text,LINKS_OWNER text, FOREIGN KEY(LINKS_OWNER) REFERENCES TABLE_FAVOURITES(FAVOURITES_TITLE))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }


    public void loadMovies() {
        if (favouriteMovies.isEmpty()) {
            //select all users from db
            Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM TABLE_FAVOURITES", null);
            while (cursor.moveToNext()) {
                String title = cursor.getString(0);
                String genre = cursor.getString(1);
                String year = cursor.getString(2);
                String rate = cursor.getString(3);
                String country = cursor.getString(4);
                String description = cursor.getString(5);
                String casts = cursor.getString(6);
                String director = cursor.getString(7);
                String posterUrl = cursor.getString(8);
                String trailer = cursor.getString(9);
                Movie movie = new Movie(title, genre, year, rate, country, description, casts, director, posterUrl, trailer);
                favouriteMovies.add(movie);
            }
            Cursor cursor2 = getWritableDatabase().rawQuery("SELECT * FROM TABLE_LINKS", null);
            while (cursor2.moveToNext()) {
                String link1 = cursor2.getString(1);
                String link2 = cursor2.getString(2);
                String link3 = cursor2.getString(3);
                String owner = cursor2.getString(4);
                Log.e("link1", link1);
                Log.e("link2", link2);
                Log.e("link3",link3);
                Log.e("owner", owner);
                for (int i = 0; i < favouriteMovies.size(); i++) {
                    if (favouriteMovies.get(i).getTitle().equals(owner)) {
                        favouriteMovies.get(i).addMovieWatchLink(link1);
                        favouriteMovies.get(i).addMovieWatchLink(link2);
                        favouriteMovies.get(i).addMovieWatchLink(link3);
                    }
                }
            }

        }

    }


    public void addMovie(String title, String genre, String year, String rate, String country, String description, String casts, String director, String posterURL, String trailer) {
        ContentValues values = new ContentValues();
        values.put("FAVOURITES_TITLE", title);
        values.put("FAVOURITES_GENRE", genre);
        values.put("FAVOURITES_YEAR", year);
        values.put("FAVOURITES_RATE", rate);
        values.put("FAVOURITES_COUNTRY", country);
        values.put("FAVOURITES_DESCRIPTION", description);
        values.put("FAVOURITES_CASTS", casts);
        values.put("FAVOURITES_DIRECTOR", director);
        values.put("FAVOURITES_POSTER_URL", posterURL);
        values.put("FAVOURITES_TRAILER", trailer);
        getWritableDatabase().insert("TABLE_FAVOURITES", null, values);
        Movie movie = new Movie(title, genre, year, rate, country, description, casts, director, posterURL, trailer);
        favouriteMovies.add(movie);

    }

    public void addLinks(String link1, String link2, String link3, String owner) {
        ContentValues values = new ContentValues();
        values.put("LINKS_LINK1", link1);
        values.put("LINKS_LINK2", link2);
        values.put("LINKS_LINK3", link3);
        values.put("LINKS_OWNER", owner);
        getWritableDatabase().insert("TABLE_LINKS", null, values);
        for (int i = 0; i < favouriteMovies.size(); i++) {
            if (favouriteMovies.get(i).getTitle().equals(owner)) {
                favouriteMovies.get(i).addMovieWatchLink(link1);
                favouriteMovies.get(i).addMovieWatchLink(link2);
                favouriteMovies.get(i).addMovieWatchLink(link3);

            }
        }


    }

    public void removeMovie(String title) {
        getWritableDatabase().delete("TABLE_FAVOURITES", "FAVOURITES_TITLE = ?", new String[]{title});
        getWritableDatabase().delete("TABLE_LINKS","LINKS_OWNER = ?",new String[]{title});
        for (int i = 0; i < favouriteMovies.size(); i++) {
            if (favouriteMovies.get(i).getTitle().equals(title)) {
                favouriteMovies.remove(favouriteMovies.get(i));
            }
        }
    }


    public ArrayList<Movie> getFavouriteMovies() {
        return favouriteMovies;
    }


    public boolean checkMovieExist(String title) {
        for (int i = 0; i < favouriteMovies.size(); i++) {
            if (favouriteMovies.get(i).getTitle().equals(title)) {
                return true;
            }
        }
        return false;

    }

}
