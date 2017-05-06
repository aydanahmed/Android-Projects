package com.sevendailymovies.android.sevendailymovies.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.sevendailymovies.android.sevendailymovies.AllMovieAdapter;
import com.sevendailymovies.android.sevendailymovies.CheckForNewDataService;
import com.sevendailymovies.android.sevendailymovies.R;
import com.sevendailymovies.android.sevendailymovies.TopMovieAdapter;
import com.sevendailymovies.android.sevendailymovies.model.Movie;
import com.sevendailymovies.android.sevendailymovies.task.GetMovieInfoTask;

import java.util.ArrayList;

public class MovieFragment extends Fragment {
    RecyclerView moviesRV;
    ProgressBar progressBar;
    private ArrayList<Movie> movies = new ArrayList<>();
    TopMovieAdapter adapter;
    MyInnerReceiver receiver;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movie, container, false);

        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        moviesRV = (RecyclerView) root.findViewById(R.id.moviesRecyclerView);
        adapter = new TopMovieAdapter(movies, getActivity());
        receiver = new MyInnerReceiver(movies, adapter, progressBar);
        if (movies.size() == 0) {
            GetMovieInfoTask task = new GetMovieInfoTask(movies, adapter, progressBar);
            task.execute("aHR0cDovL2RibW92aWVzLm5ld3MvZ2V0LnR4dA==");
        }
        moviesRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        moviesRV.setLayoutManager(new GridLayoutManager(getActivity(), 2, 1, false));
        return root;

    }

    @Override
    public void onStart() {
        if (movies.size() != 0) {
            Intent service = new Intent(getActivity(), CheckForNewDataService.class);
            getActivity().startService(service);
        }
        super.onStart();
    }

    class MyInnerReceiver extends BroadcastReceiver {
        private ArrayList<Movie> movies;
        private RecyclerView.Adapter adapter;
        private ProgressBar progressBar;

        public MyInnerReceiver(ArrayList<Movie> movies, RecyclerView.Adapter adapter, ProgressBar progressBar) {
            this.movies = movies;
            this.adapter = adapter;
            this.progressBar = progressBar;

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            GetMovieInfoTask task = new GetMovieInfoTask(movies, adapter, progressBar);
            task.execute("aHR0cDovL2RibW92aWVzLm5ld3MvZ2V0LnR4dA==");
        }
    }

    @Override
    public void onResume() {
        getActivity().registerReceiver(receiver, new IntentFilter("NewData"));
        super.onResume();
    }

    @Override
    public void onPause() {
        getActivity().unregisterReceiver(receiver);
        super.onPause();
    }
}
