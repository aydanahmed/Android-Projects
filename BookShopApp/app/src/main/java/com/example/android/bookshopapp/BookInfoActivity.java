package com.example.android.bookshopapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BookInfoActivity extends AppCompatActivity {
    private TextView title;
    private TextView priceForOne;
    private TextView quantity;
    private ImageView bookImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        title = (TextView) findViewById(R.id.viewPictureTitle);
        priceForOne= (TextView) findViewById(R.id.priceForOneBook);
        quantity = (TextView) findViewById(R.id.numberOfBooks);
        bookImg = (ImageView) findViewById(R.id.imgViewBookPic);
        Intent i = getIntent();
        title.setText(i.getStringExtra("Title"));
        priceForOne.setText(i.getStringExtra("Price"));
        quantity.setText(i.getStringExtra("Quantity"));
        bookImg.setImageResource(i.getIntExtra("Image",0));







    }



}
