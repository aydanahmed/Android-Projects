package com.example.android.bookshopapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SuccesfullActivity extends AppCompatActivity {
    private TextView txtBoughtBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succesfull);
        txtBoughtBook = (TextView) findViewById(R.id.titleOfBoughtBook);
        Intent intent = getIntent();
        txtBoughtBook.setText(intent.getStringExtra("Title"));


    }
}
