package com.example.android.bookshopapp.model;

/**
 * Created by Aydin on 29.8.2016 г..
 */
public class User {
    private String username;
    private String name;
    private String password;
    private String email;
    private String address;

    User(String username,String name,String password,String email,String address){
        this.username = username;
        this.name=name;
        this.password=password;
        this.email=email;
        this.address=address;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }
}
