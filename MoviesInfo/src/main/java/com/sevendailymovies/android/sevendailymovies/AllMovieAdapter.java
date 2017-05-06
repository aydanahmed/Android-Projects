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

public class AllMovieAdapter extends RecyclerView.Adapter<AllMovieAdapter.MovieVH> {

    private Activity activity;
    private List<Movie> movies;

    public AllMovieAdapter(ArrayList<Movie> movies, Activity activity) {
        this.movies = movies;
        this.activity = activity;
    }


    @Override
    public AllMovieAdapter.MovieVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View row = inflater.inflate(R.layout.adapter_all_movie, parent, false);
        return new AllMovieAdapter.MovieVH(row);
    }

    @Override
    public void onBindViewHolder(final AllMovieAdapter.MovieVH holder, int position) {
        final Movie movie = movies.get(holder.getAdapterPosition());

        if (DBManager.getInstance(activity).checkMovieExist(movies.get(holder.getAdapterPosition()).getTitle())) {
            holder.dontLike.setVisibility(View.GONE);
            holder.like.setVisibility(View.VISIBLE);
        } else {
            holder.dontLike.setVisibility(View.VISIBLE);
            holder.like.setVisibility(View.GONE);
        }
        holder.title.setText(movie.getTitle());

        Glide
                .with(activity)
                .load(movies.get(holder.getAdapterPosition()).getPosterURL())
                .override(400,500)
                .into(holder.poster);



        holder.dontLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBManager.getInstance(activity).addMovie(movies.get(holder.getAdapterPosition()).getTitle(), movies.get(holder.getAdapterPosition()).getGenre(), movies.get(holder.getAdapterPosition()).getYear(), movies.get(holder.getAdapterPosition()).getRate(), movies.get(holder.getAdapterPosition()).getCountry(), movies.get(holder.getAdapterPosition()).getPlot(), movies.get(holder.getAdapterPosition()).getStars(), movies.get(holder.getAdapterPosition()).getDirector(), movies.get(holder.getAdapterPosition()).getPosterURL(), movies.get(holder.getAdapterPosition()).getVideoLink());
                if (movies.get(holder.getAdapterPosition()).getMovieWatchlinks().size() == 1) {
                    DBManager.getInstance(activity).addLinks(movies.get(holder.getAdapterPosition()).getMovieWatchlinks().get(0), "nolink", "nolink", movies.get(holder.getAdapterPosition()).getTitle());
                }
                if (movies.get(holder.getAdapterPosition()).getMovieWatchlinks().size() == 2) {
                    DBManager.getInstance(activity).addLinks(movies.get(holder.getAdapterPosition()).getMovieWatchlinks().get(0), movies.get(holder.getAdapterPosition()).getMovieWatchlinks().get(1), "nolink", movies.get(holder.getAdapterPosition()).getTitle());
                }
                if (movies.get(holder.getAdapterPosition()).getMovieWatchlinks().size() == 3) {
                    DBManager.getInstance(activity).addLinks(movies.get(holder.getAdapterPosition()).getMovieWatchlinks().get(0), movies.get(holder.getAdapterPosition()).getMovieWatchlinks().get(1), movies.get(holder.getAdapterPosition()).getMovieWatchlinks().get(2), movies.get(holder.getAdapterPosition()).getTitle());
                }
                movies.get(holder.getAdapterPosition()).likeMovie();
                holder.dontLike.setVisibility(View.GONE);
                holder.like.setVisibility(View.VISIBLE);
            }
        });
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBManager.getInstance(activity).removeMovie(movies.get(holder.getAdapterPosition()).getTitle());
                holder.dontLike.setVisibility(View.VISIBLE);
                holder.like.setVisibility(View.GONE);
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
        ImageView dontLike;
        ImageView like;

        MovieVH(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_all_TV);
            poster = (ImageView) itemView.findViewById(R.id.movie_all_poster2);
            cardView = (CardView) itemView.findViewById(R.id.card_view_all);
            dontLike = (ImageView) itemView.findViewById(R.id.dontLike);
            like = (ImageView) itemView.findViewById(R.id.like);
        }
    }
}
