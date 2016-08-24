package com.example.android.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private Button buttonEUR;
    private Button buttonBGN;
    private Button buttonDolar;
    private Button buttonAddDish;
    private Button buttonMinusDish;
    private Button buttonAddDessert;
    private Button buttonMinusDessert;
    private Button buttonCalculate;
    private Button aboutUs;
    private TextView countDish;
    private TextView countDessert;
    private TextView totalPrice;
    private TextView drinkCountView;
    private CheckBox checkDeliveryTax;
    private SeekBar seekBar;
    private int numb = 0;
    private int numbDess = 0;
    private int drink = 0;
    private double sum = 0;
    private int tax = 0;
    private final double leva = 2;
    private final double dolar = 1.50;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        buttonAddDish = (Button) findViewById(R.id.button_add);
        buttonMinusDish = (Button) findViewById(R.id.button2_remove);
        buttonAddDessert = (Button) findViewById(R.id.button_addDess);
        buttonMinusDessert = (Button) findViewById(R.id.button_minusDess);
        countDish = (TextView) findViewById(R.id.textView_dishNumb);
        countDessert = (TextView) findViewById(R.id.textViewDessertCounty);
        drinkCountView = (TextView) findViewById(R.id.textViewDrinkCountit);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        checkDeliveryTax = (CheckBox) findViewById(R.id.checkBox);
        buttonCalculate = (Button) findViewById(R.id.button_calculate);
        totalPrice = (TextView) findViewById(R.id.textViewTotalRes);
        buttonEUR = (Button) findViewById(R.id.button_eur);
        buttonBGN= (Button) findViewById(R.id.button_bgn);
        buttonDolar= (Button) findViewById(R.id.button_dolar);
        aboutUs = (Button) findViewById(R.id.button_about);
        buttonAddDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numb <= 9) {
                    numb++;

                    countDish.setText(Integer.toString(numb));
                }
            }


        });
        buttonEUR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalPrice.setText(Double.toString(sum) + " EUR");
            }
        });
        buttonBGN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalPrice.setText(Double.toString(sum*leva) + " Leva");
            }
        });
        buttonDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalPrice.setText(Double.toString(sum*dolar) + " $");
            }
        });

        buttonMinusDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numb > 0) {
                    numb--;
                    countDish.setText(Integer.toString(numb));
                }
            }
        });


        buttonAddDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numbDess <= 9) {
                    numbDess++;

                    countDessert.setText(Integer.toString(numbDess));
                }
            }


        });

        buttonMinusDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numbDess > 0) {
                    numbDess--;
                    countDessert.setText(Integer.toString(numbDess));
                }
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                drink = i;
                drinkCountView.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        checkDeliveryTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tax = 10;


            }
        });
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sum = (numbDess * 2) + (numb * 5) + (drink * 2) + tax;
                totalPrice.setText(Double.toString(sum) + " EUR");
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,AboutUsActivity.class);
                startActivity(intent);
            }
        });


    }
}
