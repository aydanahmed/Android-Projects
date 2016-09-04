package com.example.android.cinemaapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.cinemaapp.Model.Cinema;
import com.example.android.cinemaapp.Model.CinemaManager;
import com.example.android.cinemaapp.Model.Movie;
import com.example.android.cinemaapp.Model.MovieManager;

public class CinemaActivity extends AppCompatActivity {
    private TextView title;
    private TextView title2;
    private TextView title3;
    private TextView address;
    private TextView address2;
    private TextView address3;
    private TextView workingHours;
    private TextView workingHours2;
    private TextView workingHours3;
    private Button program;
    private Button program2;
    private Button program3;
    private Button info;
    private Button info2;
    private Button info3;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);
        title = (TextView) findViewById(R.id.title);
        title2 = (TextView) findViewById(R.id.title2);
        title3 = (TextView) findViewById(R.id.title3);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        address = (TextView) findViewById(R.id.address_text);
        address2 = (TextView) findViewById(R.id.address_text2);
        address3 = (TextView) findViewById(R.id.address_text3);
        workingHours = (TextView) findViewById(R.id.working_hours_text);
        workingHours2 = (TextView) findViewById(R.id.working_hours_text2);
        workingHours3 = (TextView) findViewById(R.id.working_hours_text3);
        program = (Button) findViewById(R.id.button_program);
        program2 = (Button) findViewById(R.id.button_program2);
        program3 = (Button) findViewById(R.id.button_program3);
        info = (Button) findViewById(R.id.button_info);
        info2 = (Button) findViewById(R.id.button_info2);
        info3 = (Button) findViewById(R.id.button_info3);
        CinemaManager.getInstance().createCinema("Cinema one", "Amerika", "8:00-22:00", "+254657897", 100);
        CinemaManager.getInstance().createCinema("Grand", "Russia", "8:00-21:00", "+554657897", 40);
        CinemaManager.getInstance().createCinema("Cinema city", "Sofiq", "8:00-24:00", "+359254657897", 130);
        final Cinema cinemaOne = CinemaManager.getInstance().getCinema("Cinema one");

        final Cinema cinemaGrand = CinemaManager.getInstance().getCinema("Grand");
        final Cinema cinemaCity = CinemaManager.getInstance().getCinema("Cinema city");
        cinemaOne.addPictures(R.drawable.cinema_one);
        cinemaOne.addPictures(R.drawable.cine_one_2);
        title.setText(cinemaOne.getName());
        img1.setImageResource(cinemaOne.getPictures().get(0));
        address.setText(cinemaOne.getAddress());
        workingHours.setText(cinemaOne.getWorkingTime());
        cinemaGrand.addPictures(R.drawable.grand_cinema);
        cinemaGrand.addPictures(R.drawable.cine_grand_2);
        title2.setText(cinemaGrand.getName());
        img2.setImageResource(cinemaGrand.getPictures().get(0));
        address2.setText(cinemaGrand.getAddress());
        workingHours2.setText(cinemaGrand.getWorkingTime());
        cinemaCity.addPictures(R.drawable.cinema_city);
        cinemaCity.addPictures(R.drawable.cine_city_2);
        title3.setText(cinemaCity.getName());
        img3.setImageResource(cinemaCity.getPictures().get(0));
        address3.setText(cinemaCity.getAddress());
        workingHours3.setText(cinemaCity.getWorkingTime());
        final Movie expendables = MovieManager.getInstance().createMovie(R.drawable.expendables, "Expendables", "08:30-10:30", 30);
        final Movie stepUp = MovieManager.getInstance().createMovie(R.drawable.step_up, "Step up", "19:30-21:30", 50);
        final Movie iceAge = MovieManager.getInstance().createMovie(R.drawable.ice_age, "Ice Age", "15:30-17:30", 40);
        final Movie bourne = MovieManager.getInstance().createMovie(R.drawable.bourne, "Bourne", "10:30-12:30", 100);
        cinemaOne.addMovie(expendables);
        cinemaOne.addMovie(stepUp);
        cinemaOne.addMovie(iceAge);
        cinemaOne.addMovie(bourne);
        program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CinemaActivity.this, MovieActivity.class);
                intent.putExtra("Movie", cinemaOne.getMovies().get(0));
                intent.putExtra("Movie2", cinemaOne.getMovies().get(1));
                intent.putExtra("Movie3", cinemaOne.getMovies().get(2));
                intent.putExtra("Movie4", cinemaOne.getMovies().get(3));
                startActivity(intent);


            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CinemaActivity.this, CinemaInfoActivity.class);
                intent.putExtra("Cinema", cinemaOne);
                startActivity(intent);
            }
        });
        final Movie expendablesGrand = MovieManager.getInstance().createMovie(R.drawable.expendables, "Expendables", "07:30-9:30", 30);
        final Movie stepUpGrand = MovieManager.getInstance().createMovie(R.drawable.step_up, "Step up", "18:30-20:30", 15);
        final Movie iceAgeGrand = MovieManager.getInstance().createMovie(R.drawable.ice_age, "Ice Age", "14:30-16:30", 10);
        final Movie bourneGrand = MovieManager.getInstance().createMovie(R.drawable.bourne, "Bourne", "09:30-11:30", 20);
        cinemaGrand.addMovie(expendablesGrand);
        cinemaGrand.addMovie(stepUpGrand);
        cinemaGrand.addMovie(iceAgeGrand);
        cinemaGrand.addMovie(bourneGrand);
        program2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CinemaActivity.this, MovieActivity.class);
                intent.putExtra("Movie", cinemaGrand.getMovies().get(0));
                intent.putExtra("Movie2", cinemaGrand.getMovies().get(1));
                intent.putExtra("Movie3", cinemaGrand.getMovies().get(2));
                intent.putExtra("Movie4", cinemaGrand.getMovies().get(3));
                startActivity(intent);


            }
        });

        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CinemaActivity.this, CinemaInfoActivity.class);
                intent.putExtra("Cinema", cinemaGrand);
                startActivity(intent);
            }
        });

        final Movie expendablesCity = MovieManager.getInstance().createMovie(R.drawable.expendables, "Expendables", "09:30-11:15", 20);
        final Movie stepUpCity = MovieManager.getInstance().createMovie(R.drawable.step_up, "Step up", "20:30-22:30", 40);
        final Movie iceAgeCity = MovieManager.getInstance().createMovie(R.drawable.ice_age, "Ice Age", "16:30-18:30", 60);
        final Movie bourneCity = MovieManager.getInstance().createMovie(R.drawable.bourne, "Bourne", "11:30-13:30", 200);
        cinemaCity.addMovie(expendablesCity);
        cinemaCity.addMovie(stepUpCity);
        cinemaCity.addMovie(iceAgeCity);
        cinemaCity.addMovie(bourneCity);
        program3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CinemaActivity.this, MovieActivity.class);
                intent.putExtra("Movie", cinemaCity.getMovies().get(0));
                intent.putExtra("Movie2", cinemaCity.getMovies().get(1));
                intent.putExtra("Movie3", cinemaCity.getMovies().get(2));
                intent.putExtra("Movie4", cinemaCity.getMovies().get(3));
                startActivity(intent);


            }
        });

        info3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CinemaActivity.this, CinemaInfoActivity.class);
                intent.putExtra("Cinema", cinemaCity);
                startActivity(intent);
            }
        });



    }
}
