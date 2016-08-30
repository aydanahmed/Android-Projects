package com.example.android.bookshopapp.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Aydin on 30.8.2016 г..
 */
public class Book {
    private String title;
    private int image;
    private double price;
    private int quantity;


    public Book(String title, int image, double price,int quantity){
        this.title=title;
        this.image=image;
        this.price=price;
        this.quantity=quantity;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public int getQuantity() {
        return quantity;
    }
}
