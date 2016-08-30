package com.example.android.bookshopapp.model;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by Aydin on 29.8.2016 г..
 */
public class UserManager {
    private static UserManager ourInstance = new UserManager();
    private HashMap<String, User> userInfo;

    public static UserManager getInstance() {
        return ourInstance;
    }

    private UserManager() {
            userInfo = new HashMap<>();

    }
    public HashMap<String, User> getUserInfo() {
        return userInfo;
    }


    public boolean isLogin(String username, String password) {

        if (userInfo.containsKey(username) && userInfo.get(username).getPassword().equals(password)) {
            return true;
        }
        return false;

    }

    public void userRegister(String name, String username, String password, String email) {
        if (name == null || name.isEmpty() || username == null || username.isEmpty()
                || userInfo.containsKey(username) || password == null || password.isEmpty()
                || password.length() < 6 || username.length() > 10 || username.length() < 6
                || !isValidEmail(email)) {
            return;
        }
        User u = new User(username, name, password, email);
        userInfo.put(username, u);

    }

    public final static boolean isValidEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

}
