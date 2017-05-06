package com.sevendailymovies.android.sevendailymovies;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sevendailymovies.android.sevendailymovies.activity.MovieInfoActivity;
import com.sevendailymovies.android.sevendailymovies.model.Database.DBManager;
import com.sevendailymovies.android.sevendailymovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieFavouriteAdapter extends RecyclerView.Adapter<MovieFavouriteAdapter.MovieVH> {
    private Activity activity;
    private List<Movie> movies;

    public MovieFavouriteAdapter(ArrayList<Movie> movies, Activity activity) {
        this.movies = movies;
        this.activity = activity;
    }

    @Override
    public MovieFavouriteAdapter.MovieVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View row = inflater.inflate(R.layout.adapter_favourite, parent, false);
        return new MovieFavouriteAdapter.MovieVH(row);
    }

    @Override
    public void onBindViewHolder(final MovieFavouriteAdapter.MovieVH holder, final int position) {
        final Movie movie = movies.get(holder.getAdapterPosition());
        holder.title.setText(movie.getTitle());
        Glide
                .with(activity)
                .load(movies.get(holder.getAdapterPosition()).getPosterURL())
                .override(400,300)
                .into(holder.poster);
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.title.setVisibility(View.GONE);
                holder.poster.setVisibility(View.GONE);
                holder.like.setVisibility(View.GONE);
                notifyItemRemoved(holder.getAdapterPosition());
                DBManager.getInstance(activity).removeMovie(movie.getTitle());
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MovieInfoActivity.class);
                intent.putExtra("Title", movies.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("Director", movies.get(holder.getAdapterPosition()).getDirector());
                intent.putExtra("Plot", movies.get(holder.getAdapterPosition()).getPlot());
                intent.putExtra("Stars", movies.get(holder.getAdapterPosition()).getStars());
                intent.putExtra("Rate", movies.get(holder.getAdapterPosition()).getRate());
                intent.putExtra("movieLink", movies.get(holder.getAdapterPosition()).getVideoLink());
                intent.putExtra("movieYear", movies.get(holder.getAdapterPosition()).getYear());
                intent.putExtra("movieGross", movies.get(holder.getAdapterPosition()).getGross());
                intent.putExtra("movieWeekend", movies.get(holder.getAdapterPosition()).getWeekend());
                intent.putExtra("movieCountry", movies.get(holder.getAdapterPosition()).getCountry());
                intent.putExtra("movieGenre", movies.get(holder.getAdapterPosition()).getGenre());
                if (movies.get(holder.getAdapterPosition()).checkMovieWatchLinks()) {
                    intent.putExtra("movieWatchLinks", movies.get(holder.getAdapterPosition()).getMovieWatchlinks());
                }
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieVH extends RecyclerView.ViewHolder {
        TextView title;
        ImageView poster;
        CardView cardView;
        ImageView like;

        MovieVH(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_favourite_TV);
            poster = (ImageView) itemView.findViewById(R.id.movie_favourite_poster2);
            cardView = (CardView) itemView.findViewById(R.id.card_view_favourite);
            like = (ImageView) itemView.findViewById(R.id.like_favourite);

        }
    }
}
