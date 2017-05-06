package com.sevendailymovies.android.sevendailymovies.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sevendailymovies.android.sevendailymovies.MovieFavouriteAdapter;
import com.sevendailymovies.android.sevendailymovies.R;
import com.sevendailymovies.android.sevendailymovies.activity.MainActivity;
import com.sevendailymovies.android.sevendailymovies.model.Database.DBManager;

public class FavouriteMovieFragment extends Fragment {
    MovieFavouriteAdapter adapter;
    RecyclerView favouriteRV;
    Activity context;
    TextView emptyMovies;

    @Override
    public void onAttach(Context context) {
        this.context = (MainActivity) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favourite_movie, container, false);
        emptyMovies = (TextView) root.findViewById(R.id.emptyFavourite);
        if(DBManager.getInstance(context).getFavouriteMovies().isEmpty()){
            emptyMovies.setVisibility(View.VISIBLE);
        }else{
            emptyMovies.setVisibility(View.GONE);
        }
        favouriteRV = (RecyclerView) root.findViewById(R.id.favouritesRecyclerView);
        adapter = new MovieFavouriteAdapter(DBManager.getInstance(context).getFavouriteMovies(), getActivity());
        favouriteRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        favouriteRV.setLayoutManager(new GridLayoutManager(getActivity(), 2, 1, false));
        return root;
    }

}
