package com.example.android.cinemaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.cinemaapp.Model.Movie;

import org.w3c.dom.Text;

public class ReserveActivity extends AppCompatActivity {
    private TextView numbTicket;
    private TextView filmName;
    private TextView workingTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        numbTicket = (TextView) findViewById(R.id.numberOfTicketBuy);
        filmName = (TextView) findViewById(R.id.filmNameWatch);
        workingTime= (TextView) findViewById(R.id.filmTime);
        Intent intent = getIntent();
        numbTicket.setText(intent.getStringExtra("Tickets"));
        Movie movie = (Movie) intent.getSerializableExtra("Movie");
        filmName.setText(movie.getTitle());
        workingTime.setText(movie.getWorkingTime());







    }
}
