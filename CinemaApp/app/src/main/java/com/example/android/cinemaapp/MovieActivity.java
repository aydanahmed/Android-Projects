package com.example.android.cinemaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.cinemaapp.Model.Movie;

public class MovieActivity extends AppCompatActivity {
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private TextView workinghours1;
    private TextView workinghours2;
    private TextView workinghours3;
    private TextView workinghours4;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        img1 = (ImageView) findViewById(R.id.imgMovie1);
        img2 = (ImageView) findViewById(R.id.imgMovie2);
        img3 = (ImageView) findViewById(R.id.imgMovie3);
        img4 = (ImageView) findViewById(R.id.imgMovie4);
        workinghours1 = (TextView) findViewById(R.id.working_hours_movie);
        workinghours2 = (TextView) findViewById(R.id.working_hours_movie2);
        workinghours3 = (TextView) findViewById(R.id.working_hours_movie3);
        workinghours4 = (TextView) findViewById(R.id.working_hours_movie4);
        button1 = (Button) findViewById(R.id.button_movie1);
        button2 = (Button) findViewById(R.id.button_movie2);
        button3 = (Button) findViewById(R.id.button_movie3);
        button4 = (Button) findViewById(R.id.button_movie4);
        Intent intent = getIntent();
        final Movie movie = (Movie) intent.getSerializableExtra("Movie");
        img1.setImageResource(movie.getPicture());
        workinghours1.setText(movie.getWorkingTime());
        button1.setText(movie.getTitle());
       final int tickets = movie.getTicket();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieActivity.this,GetTicketActivity.class);
                intent.putExtra("Tickets",tickets);
                intent.putExtra("Movie",movie);
                startActivity(intent);
            }
        });
        final Movie movie2 = (Movie) intent.getSerializableExtra("Movie2");
        img2.setImageResource(movie2.getPicture());
        workinghours2.setText(movie2.getWorkingTime());
        button2.setText(movie2.getTitle());
        final int tickets2 = movie2.getTicket();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieActivity.this,GetTicketActivity.class);
                intent.putExtra("Tickets",tickets2);
                intent.putExtra("Movie",movie2);
                startActivity(intent);
            }
        });
        final Movie movie3 = (Movie) intent.getSerializableExtra("Movie3");
        img3.setImageResource(movie3.getPicture());
        workinghours3.setText(movie3.getWorkingTime());
        button3.setText(movie3.getTitle());
        final int tickets3 = movie3.getTicket();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieActivity.this,GetTicketActivity.class);
                intent.putExtra("Tickets",tickets3);
                intent.putExtra("Movie",movie3);
                startActivity(intent);
            }
        });
        final Movie movie4 = (Movie) intent.getSerializableExtra("Movie4");
        img4.setImageResource(movie4.getPicture());
        workinghours4.setText(movie4.getWorkingTime());
        button4.setText(movie4.getTitle());
        final int tickets4 = movie4.getTicket();
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieActivity.this,GetTicketActivity.class);
                intent.putExtra("Tickets",tickets4);
                intent.putExtra("Movie",movie4);
                startActivity(intent);
            }
        });


    }
}
