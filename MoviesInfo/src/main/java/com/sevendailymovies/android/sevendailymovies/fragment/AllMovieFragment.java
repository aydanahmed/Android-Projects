package com.sevendailymovies.android.sevendailymovies.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sevendailymovies.android.sevendailymovies.AllMovieAdapter;
import com.sevendailymovies.android.sevendailymovies.CheckForNewMoviesAdded;
import com.sevendailymovies.android.sevendailymovies.R;
import com.sevendailymovies.android.sevendailymovies.SearchMovieListener;
import com.sevendailymovies.android.sevendailymovies.model.Movie;
import com.sevendailymovies.android.sevendailymovies.task.GetMovieInfoTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class AllMovieFragment extends Fragment {
    RecyclerView moviesRV;
    ProgressBar progressBar;
    Spinner spinner;
    Activity activity;
    MyInnerReceiver receiver;
    private ArrayList<Movie> movies = new ArrayList<>();
    AllMovieAdapter adapter;
    private static final String[] categories = {"All", "Action", "Adventure", "Comedy", "Thriller", "Horror", "Documentary", "Crime", "Musical", "Drama", "Biography", "Sci-Fi", "History"};

    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_all_movie, container, false);
        final SearchView search = (SearchView) root.findViewById(R.id.search);
        spinner = (Spinner) root.findViewById(R.id.spinner);
        ArrayAdapter<String> spinnedAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, categories);
        spinnedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnedAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        putCategoryToAdapter(movies);
                        addSearchListener(movies, search);
                        break;
                    case 1:
                        if (Movie.getMoviesFromCategory("Action") != null) {
                            putCategoryToAdapter(Movie.getMoviesFromCategory("Action"));
                            addSearchListener(Movie.getMoviesFromCategory("Action"), search);
                        } else {
                            Toast.makeText(getActivity(), "No have movies in selected category!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if (Movie.getMoviesFromCategory("Adventure") != null) {
                            putCategoryToAdapter(Movie.getMoviesFromCategory("Adventure"));
                            addSearchListener(Movie.getMoviesFromCategory("Adventure"), search);
                        } else {
                            Toast.makeText(getActivity(), "No have movies in selected category!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        if (Movie.getMoviesFromCategory("Comedy") != null) {
                            putCategoryToAdapter(Movie.getMoviesFromCategory("Comedy"));
                            addSearchListener(Movie.getMoviesFromCategory("Comedy"), search);
                        } else {
                            Toast.makeText(getActivity(), "No have movies in selected category!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 4:
                        if (Movie.getMoviesFromCategory("Thriller") != null) {
                            putCategoryToAdapter(Movie.getMoviesFromCategory("Thriller"));
                            addSearchListener(Movie.getMoviesFromCategory("Thriller"), search);
                        } else {
                            Toast.makeText(getActivity(), "No have movies in selected category!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 5:
                        if (Movie.getMoviesFromCategory("Horror") != null) {
                            putCategoryToAdapter(Movie.getMoviesFromCategory("Horror"));
                            addSearchListener(Movie.getMoviesFromCategory("Horror"), search);
                        } else {
                            Toast.makeText(getActivity(), "No have movies in selected category!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 6:
                        if (Movie.getMoviesFromCategory("Documentary") != null) {
                            putCategoryToAdapter(Movie.getMoviesFromCategory("Documentary"));
                            addSearchListener(Movie.getMoviesFromCategory("Documentary"), search);
                        } else {
                            Toast.makeText(getActivity(), "No have movies in selected category!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 7:
                        if (Movie.getMoviesFromCategory("Crime") != null) {
                            putCategoryToAdapter(Movie.getMoviesFromCategory("Crime"));
                            addSearchListener(Movie.getMoviesFromCategory("Crime"), search);
                        } else {
                            Toast.makeText(getActivity(), "No have movies in selected category!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 8:
                        if (Movie.getMoviesFromCategory("Musical") != null) {
                            putCategoryToAdapter(Movie.getMoviesFromCategory("Musical"));
                            addSearchListener(Movie.getMoviesFromCategory("Musical"), search);
                        } else {
                            Toast.makeText(getActivity(), "No have movies in selected category!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 9:
                        if (Movie.getMoviesFromCategory("Drama") != null) {
                            putCategoryToAdapter(Movie.getMoviesFromCategory("Drama"));
                            addSearchListener(Movie.getMoviesFromCategory("Drama"), search);
                        } else {
                            Toast.makeText(getActivity(), "No have movies in selected category!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 10:
                        if (Movie.getMoviesFromCategory("Biography") != null) {
                            putCategoryToAdapter(Movie.getMoviesFromCategory("Biography"));
                            addSearchListener(Movie.getMoviesFromCategory("Biography"), search);
                        } else {
                            Toast.makeText(getActivity(), "No have movies in selected category!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 11:
                        if (Movie.getMoviesFromCategory("Sci-Fi") != null) {
                            putCategoryToAdapter(Movie.getMoviesFromCategory("Sci-Fi"));
                            addSearchListener(Movie.getMoviesFromCategory("Sci-Fi"), search);
                        } else {
                            Toast.makeText(getActivity(), "No have movies in selected category!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 12:
                        if (Movie.getMoviesFromCategory("History") != null) {
                            putCategoryToAdapter(Movie.getMoviesFromCategory("History"));
                            addSearchListener(Movie.getMoviesFromCategory("History"), search);
                        } else {
                            Toast.makeText(getActivity(), "No have movies in selected category!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        progressBar = (ProgressBar) root.findViewById(R.id.progressBarAllMovie);
        moviesRV = (RecyclerView) root.findViewById(R.id.moviesRecyclerViewAllMovie);
        receiver = new MyInnerReceiver();
        adapter = new AllMovieAdapter(movies, getActivity());
        if (!isNetworkAvailable()) {
            Toast.makeText(activity, "No have internet connection", Toast.LENGTH_SHORT).show();
        }
        if (movies.size() == 0) {
            GetMovieInfoTask task = new GetMovieInfoTask(movies,adapter,progressBar);
            task.execute("aHR0cDovL2RibW92aWVzLm5ld3MvbW92aWVzLnR4dA==");
        }
        moviesRV.setAdapter(adapter);
        moviesRV.setLayoutManager(new GridLayoutManager(getActivity(), 3, 1, false));
        search.setOnQueryTextListener(new SearchMovieListener(movies, moviesRV, adapter, getActivity()));
        return root;
    }


    @Override
    public void onStart() {
        if (movies.size() != 0) {
            Intent service = new Intent(getActivity(), CheckForNewMoviesAdded.class);
            getActivity().startService(service);
        }
        super.onStart();
    }


    class MyInnerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            GetMovieInfoTask task = new GetMovieInfoTask(movies,adapter,progressBar);
            task.execute("aHR0cDovL2RibW92aWVzLm5ld3MvbW92aWVzLnR4dA==");
        }
    }

    @Override
    public void onResume() {
        getActivity().registerReceiver(receiver, new IntentFilter("NewMoviesAdded"));
        super.onResume();
    }

    @Override
    public void onPause() {
        getActivity().unregisterReceiver(receiver);
        super.onPause();
    }

    private void putCategoryToAdapter(ArrayList<Movie> moviesAdd) {
        moviesRV.setLayoutManager(new GridLayoutManager(getActivity(), 3, 1, false));
        adapter = new AllMovieAdapter(moviesAdd, getActivity());
        moviesRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    private void addSearchListener(ArrayList<Movie> moviesList, SearchView search) {
        search.setOnQueryTextListener(new SearchMovieListener(moviesList, moviesRV, adapter, getActivity()));
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

}
