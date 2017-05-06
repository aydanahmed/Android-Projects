package com.sevendailymovies.android.sevendailymovies;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import com.sevendailymovies.android.sevendailymovies.model.Movie;
import java.util.ArrayList;

public class SearchMovieListener implements SearchView.OnQueryTextListener {
    private ArrayList<Movie> movies;
    private RecyclerView moviesRV;
    private RecyclerView.Adapter adapter;
    private Activity activity;

    public SearchMovieListener(ArrayList<Movie> movies, RecyclerView recyclerView, RecyclerView.Adapter adapter, Activity activity) {
        this.movies = movies;
        this.moviesRV = recyclerView;
        this.adapter = adapter;
        this.activity = activity;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        query = query.toLowerCase();

        ArrayList<Movie> filteredList = new ArrayList<>();

        for (int i = 0; i < movies.size(); i++) {

            final String text = movies.get(i).getTitle().toLowerCase();
            if (text.contains(query)) {

                filteredList.add(movies.get(i));
            }
        }

        moviesRV.setLayoutManager(new GridLayoutManager(activity, 3, 1, false));
        adapter = new AllMovieAdapter(filteredList, (activity));
        moviesRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();  // data set changed
        return true;
    }
}
