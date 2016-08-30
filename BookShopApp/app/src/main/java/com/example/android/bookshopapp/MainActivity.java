package com.example.android.bookshopapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.bookshopapp.model.Book;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView book1Title;
    private TextView book2Title;
    private TextView book3Title;
    private Button book1View;
    private Button book2View;
    private Button book3View;
    private Button book1Buy;
    private Button book2Buy;
    private Button book3Buy;
    private Spinner book1Spinner;
    private Spinner book2Spinner;
    private Spinner book3Spinner;
    private Button book1Plus;
    private Button book2Plus;
    private Button book3Plus;
    private Button book1Minus;
    private Button book2Minus;
    private Button book3Minus;
    private TextView book1Currency;
    private TextView book2Currency;
    private TextView book3Currency;
    private TextView book1Quantity;
    private TextView book2Quantity;
    private TextView book3Quantity;
    private ImageView book1ImgView;
    private ImageView book2ImgView;
    private ImageView book3ImgView;
    private int quantity1 = 0;
    private int quantity2 = 0;
    private int quantity3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        book1Title = (TextView) findViewById(R.id.book1Title);
        book2Title = (TextView) findViewById(R.id.book2Title);
        book3Title = (TextView) findViewById(R.id.book3Title);
        book1Quantity = (TextView) findViewById(R.id.book1Quantity);
        book2Quantity = (TextView) findViewById(R.id.book2Quantity);
        book3Quantity = (TextView) findViewById(R.id.book3Quantity);
        book1Currency = (TextView) findViewById(R.id.book1Currency);
        book2Currency = (TextView) findViewById(R.id.book2Currency);
        book3Currency = (TextView) findViewById(R.id.book3Currency);
        book1Plus = (Button) findViewById(R.id.btnBook1Plus);
        book2Plus = (Button) findViewById(R.id.book2Plus);
        book3Plus = (Button) findViewById(R.id.book3Plus);
        book1Minus = (Button) findViewById(R.id.btnBook1Minus);
        book2Minus = (Button) findViewById(R.id.book2Minus);
        book3Minus = (Button) findViewById(R.id.book3Minus);
        book1Buy = (Button) findViewById(R.id.btnBuyBook1);
        book2Buy = (Button) findViewById(R.id.btnBook2Buy);
        book3Buy = (Button) findViewById(R.id.btnBook3Buy);
        book1View = (Button) findViewById(R.id.btnViewBook1);
        book2View = (Button) findViewById(R.id.btnBook2View);
        book3View = (Button) findViewById(R.id.btnBook3View);
        book1Spinner = (Spinner) findViewById(R.id.spinnerBook1);
        book2Spinner = (Spinner) findViewById(R.id.book2Spinner);
        book3Spinner = (Spinner) findViewById(R.id.book3Spinner);
        book1ImgView = (ImageView) findViewById(R.id.book1ImgView);
        book2ImgView = (ImageView) findViewById(R.id.book2Img);
        book3ImgView = (ImageView) findViewById(R.id.book3Img);
        final Book warcraft1 = new Book("Warcraft 1 part", R.drawable.book1, 10, 2);
        final Book warcraft2 = new Book("Warcraft 2 part", R.drawable.book2, 15, 3);
        final Book warcraft3 = new Book("Warcraft 3 part", R.drawable.book3, 20, 5);
        book1ImgView.setImageResource(warcraft1.getImage());
        book2ImgView.setImageResource(warcraft2.getImage());
        book3ImgView.setImageResource(warcraft3.getImage());
        book1Title.setText(warcraft1.getTitle());
        book2Title.setText(warcraft2.getTitle());
        book3Title.setText(warcraft3.getTitle());
        ArrayList<String> currency = new ArrayList<>();
        currency.add("BGN");
        currency.add("Euro");
        currency.add("$");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, currency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        book1Spinner.setAdapter(adapter);
        book1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getSelectedItem().toString()) {
                    case "BGN":
                        book1Currency.setText(String.valueOf(warcraft1.getPrice()));
                        break;
                    case "Euro":
                        book1Currency.setText(String.valueOf(warcraft1.getPrice() * 2));
                        break;
                    case "$":
                        book1Currency.setText(String.valueOf(warcraft1.getPrice() * 1.70));
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        book1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent(MainActivity.this, BookInfoActivity.class);
                data.putExtra("Title", warcraft1.getTitle());
                data.putExtra("Image", warcraft1.getImage());
                data.putExtra("Quantity", String.valueOf(warcraft1.getQuantity()));
                data.putExtra("Price", String.valueOf(warcraft1.getPrice()));
                startActivity(data);


            }
        });
        book1Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SuccesfullActivity.class);
                intent.putExtra("Title",warcraft1.getTitle());
                startActivity(intent);
            }
        });

        book1Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity1 >= 0 && quantity1 < warcraft1.getQuantity()) {
                    book1Quantity.setText(String.valueOf(++quantity1));
                }
            }
        });
        book1Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity1 > 0) {
                    book1Quantity.setText(String.valueOf(--quantity1));
                }
            }
        });
        book2Spinner.setAdapter(adapter);
        book2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getSelectedItem().toString()) {
                    case "BGN":
                        book2Currency.setText(String.valueOf(warcraft2.getPrice()));
                        break;
                    case "Euro":
                        book2Currency.setText(String.valueOf(warcraft2.getPrice() * 2));
                        break;
                    case "$":
                        book2Currency.setText(String.valueOf(warcraft2.getPrice() * 1.70));
                        break;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        book2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent(MainActivity.this, BookInfoActivity.class);
                data.putExtra("Title", warcraft2.getTitle());
                data.putExtra("Image", warcraft2.getImage());
                data.putExtra("Quantity", String.valueOf(warcraft2.getQuantity()));
                data.putExtra("Price", String.valueOf(warcraft2.getPrice()));
                startActivity(data);


            }
        });
        book2Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SuccesfullActivity.class);
                intent.putExtra("Title",warcraft2.getTitle());
                startActivity(intent);
            }
        });

        book2Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity2 >= 0 && quantity2 < warcraft2.getQuantity()) {
                    book2Quantity.setText(String.valueOf(++quantity2));
                }
            }
        });
        book2Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity2 > 0) {
                    book2Quantity.setText(String.valueOf(--quantity2));
                }
            }
        });
        book3Spinner.setAdapter(adapter);
        book3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getSelectedItem().toString()) {
                    case "BGN":
                        book3Currency.setText(String.valueOf(warcraft3.getPrice()));
                        break;
                    case "Euro":
                        book3Currency.setText(String.valueOf(warcraft3.getPrice() * 2));
                        break;
                    case "$":
                        book3Currency.setText(String.valueOf(warcraft3.getPrice() * 1.70));
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        book3View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent(MainActivity.this, BookInfoActivity.class);
                data.putExtra("Title", warcraft3.getTitle());
                data.putExtra("Image", warcraft3.getImage());
                data.putExtra("Quantity", String.valueOf(warcraft3.getQuantity()));
                data.putExtra("Price", String.valueOf(warcraft3.getPrice()));
                startActivity(data);


            }
        });
        book3Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity3 >= 0 && quantity3 < warcraft3.getQuantity()) {
                    book3Quantity.setText(String.valueOf(++quantity3));
                }
            }
        });
        book3Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SuccesfullActivity.class);
                intent.putExtra("Title",warcraft3.getTitle());
                startActivity(intent);
            }
        });
        book3Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity3 > 0) {
                    book3Quantity.setText(String.valueOf(--quantity3));
                }
            }
        });

    }


}
