package com.example.android.cinemaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.cinemaapp.Model.Cinema;

public class CinemaInfoActivity extends AppCompatActivity {
    private ImageView img1;
    private ImageView img2;
    private TextView txtAddress;
    private TextView txtOpen;
    private TextView txtPhone;
    private TextView txtPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_info);
        img1 = (ImageView) findViewById(R.id.firstImage);
        img2 = (ImageView) findViewById(R.id.secondImage);
        txtAddress = (TextView) findViewById(R.id.address_text_more);
        txtOpen = (TextView) findViewById(R.id.working_hours_text_more);
        txtPhone = (TextView) findViewById(R.id.telephone_more);
        txtPlaces = (TextView) findViewById(R.id.parking_more);
        Intent intent = getIntent();
        Cinema cinema = (Cinema) intent.getSerializableExtra("Cinema");
        img1.setImageResource(cinema.getPictures().get(0));
        img2.setImageResource(cinema.getPictures().get(1));
        txtAddress.setText(cinema.getAddress());
        txtOpen.setText(cinema.getWorkingTime());
        txtPhone.setText(cinema.getTelephone());
        txtPlaces.setText(String.valueOf(cinema.getParkingPlace()));







    }
}
