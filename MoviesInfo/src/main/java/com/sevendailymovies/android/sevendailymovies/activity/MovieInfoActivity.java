package com.sevendailymovies.android.sevendailymovies.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sevendailymovies.android.sevendailymovies.PlayerConfig;
import com.sevendailymovies.android.sevendailymovies.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class MovieInfoActivity extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    YouTubePlayer.OnFullscreenListener onFullscreenListener;
    private ImageView button;
    String movieLink;
    private static final int PORTRAIT_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;
    private static final int LANDSCAPE_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
    private YouTubePlayer mPlayer = null;
    private boolean mAutoRotation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_movie_info);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.video_movie);
        mAutoRotation = Settings.System.getInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0) == 1;
        button = (ImageView) findViewById(R.id.play_button);
        onFullscreenListener = new YouTubePlayer.OnFullscreenListener() {
            @Override
            public void onFullscreen(boolean fullsize) {
                if (fullsize) {
                    setRequestedOrientation(LANDSCAPE_ORIENTATION);
                } else {
                    setRequestedOrientation(PORTRAIT_ORIENTATION);
                }
            }
        };
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {


            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                                boolean wasRestored) {
                mPlayer = player;
                player.setOnFullscreenListener(onFullscreenListener);

                if (mAutoRotation) {
                    player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION
                            | YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI
                            | YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE
                            | YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
                    player.loadVideo(movieLink);
                } else {
                    player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION
                            | YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI
                            | YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
                    player.loadVideo(movieLink);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize(PlayerConfig.API_KEY, onInitializedListener);
                button.setVisibility(View.GONE);
            }
        });
        TextView movieTitle = (TextView) findViewById(R.id.movie_title);
        TextView movieDirector = (TextView) findViewById(R.id.movie_director);
        RatingBar movieRate = (RatingBar) findViewById(R.id.movie_rate);
        TextView movieActors = (TextView) findViewById(R.id.movie_actors);
        TextView moviePlot = (TextView) findViewById(R.id.movie_plot);
        TextView movieRateText = (TextView) findViewById(R.id.movie_rate_text);
        TextView movieYear = (TextView) findViewById(R.id.movie_year);
        TextView movieGenre = (TextView) findViewById(R.id.movie_genre);
        TextView movieYearTV = (TextView) findViewById(R.id.movie_year_text_view);
        TextView movieGrossTV = (TextView) findViewById(R.id.movie_gross_text_view);
        TextView movieGross = (TextView) findViewById(R.id.movie_gross);
        TextView movieWeekendTV = (TextView) findViewById(R.id.movie_weekend_text_view);
        TextView movieWeekend = (TextView) findViewById(R.id.movie_weekend);
        TextView movieCountryTV = (TextView) findViewById(R.id.movie_country_text_view);
        TextView movieCountry = (TextView) findViewById(R.id.movie_country);

        Button buttonWatchMovie1 = (Button) findViewById(R.id.button_1_watch_movie);
        Button buttonWatchMovie2 = (Button) findViewById(R.id.button_2_watch_movie);
        Button buttonWatchMovie3 = (Button) findViewById(R.id.button_3_watch_movie);
        String title = getIntent().getStringExtra("Title");
        String director = getIntent().getStringExtra("Director");
        String plot = getIntent().getStringExtra("Plot");
        String stars = getIntent().getStringExtra("Stars");
        String rate = getIntent().getStringExtra("Rate");
        String year = getIntent().getStringExtra("movieYear");
        String gross = getIntent().getStringExtra("movieGross");
        String weekend = getIntent().getStringExtra("movieWeekend");
        String country = getIntent().getStringExtra("movieCountry");
        if(year == null){
            movieYearTV.setVisibility(View.GONE);
            movieYear.setVisibility(View.GONE);
        }
        if(country == null){
            movieCountryTV.setVisibility(View.GONE);
            movieCountry.setVisibility(View.GONE);
        }
        if(weekend == null){
            movieWeekendTV.setVisibility(View.GONE);
            movieWeekend.setVisibility(View.GONE);
        }
        if(gross == null){
            movieGrossTV.setVisibility(View.GONE);
            movieGross.setVisibility(View.GONE);
        }
        String genre = getIntent().getStringExtra("movieGenre");
        movieLink = getIntent().getStringExtra("movieLink");
        if (getIntent().getStringArrayListExtra("movieWatchLinks") != null) {
            final ArrayList<String> links = getIntent().getStringArrayListExtra("movieWatchLinks");

            if (links.size() == 1) {
                buttonWatchMovie1.setVisibility(View.VISIBLE);
                buttonWatchMovie1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(links.get(0)));
                        startActivity(intent);
                    }
                });
            } else if (links.size() == 2) {
                buttonWatchMovie1.setVisibility(View.VISIBLE);
                buttonWatchMovie1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(links.get(0)));
                        startActivity(intent);
                    }
                });
                buttonWatchMovie2.setVisibility(View.VISIBLE);
                buttonWatchMovie2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(links.get(1)));
                        startActivity(intent);
                    }
                });
            } else if (links.size() == 3) {
                buttonWatchMovie1.setVisibility(View.VISIBLE);
                buttonWatchMovie1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(links.get(0)));
                        startActivity(intent);
                    }
                });
                buttonWatchMovie2.setVisibility(View.VISIBLE);
                buttonWatchMovie2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(links.get(1)));
                        startActivity(intent);
                    }
                });
                buttonWatchMovie3.setVisibility(View.VISIBLE);
                buttonWatchMovie3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(links.get(2)));
                        startActivity(intent);
                    }
                });
            }
        }
        movieTitle.setText(title);
        movieDirector.setText(director);
        movieRate.setNumStars(10);
        movieRate.setMax(10);
        movieRate.setRating(Float.parseFloat(rate));
        movieRateText.setText("(" + rate + ")");
        movieActors.setText(stars);
        moviePlot.setText(plot);
        movieYear.setText(year);
        movieGenre.setText(genre);
        movieGross.setText(gross);
        movieWeekend.setText(weekend);
        movieCountry.setText(country);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (mPlayer != null)
                mPlayer.setFullscreen(true);
        }
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (mPlayer != null)
                mPlayer.setFullscreen(false);
        }
    }


}
