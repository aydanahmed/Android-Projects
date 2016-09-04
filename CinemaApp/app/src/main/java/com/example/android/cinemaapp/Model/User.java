package com.example.android.cinemaapp.Model;

/**
 * Created by Aydin on 4.9.2016 г..
 */
public class User {
    private String username;
    private String password;
    private String age;
    private String email;
    private String name;

    public User(String name,String username, String password, String age, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}
