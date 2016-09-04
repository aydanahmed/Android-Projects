package com.example.android.cinemaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.cinemaapp.Model.Movie;

public class GetTicketActivity extends AppCompatActivity {
    private Button plus;
    private  Button minus;
    private TextView numbTickets;
    private  Button reserve;
    private int ticketsNumber=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_ticket);

        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        reserve = (Button) findViewById(R.id.reserve);
        numbTickets = (TextView) findViewById(R.id.ticketsNumber);
        Intent intent = getIntent();
        final Movie movie = (Movie) intent.getSerializableExtra("Movie");
        final int data = intent.getIntExtra("Tickets",0);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ticketsNumber>=0 && ticketsNumber<data){
                    numbTickets.setText(String.valueOf(++ticketsNumber));
                }

            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ticketsNumber>0 ){
                    numbTickets.setText(String.valueOf(--ticketsNumber));
                }

            }
        });


        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetTicketActivity.this,ReserveActivity.class);
                intent.putExtra("Tickets",String.valueOf(ticketsNumber));
                intent.putExtra("Movie",movie);
                startActivity(intent);
            }
        });




    }
}
