package com.example.android.cinemaapp.Model;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by Aydin on 4.9.2016 г..
 */
public class UserManager {
    private static UserManager ourInstance = new UserManager();
    private static HashMap<String,User> userInfo;
    public static UserManager getInstance() {
        return ourInstance;
    }

    private UserManager() {
        userInfo = new HashMap<>();
    }




    public void register(String name,String username,String password,String age,String email){
        User user = new User(name,username,password,age,email);
        userInfo.put(username,user);
    }



    public boolean existUsername(String username){
        if(userInfo.containsKey(username)){
            Log.e("User","Username is exist");
            return true;
        }
        return false;

    }

    public boolean loginCheck(String username,String password){
        if(!userInfo.containsKey(username)){
            Log.e("USER","Username is doesnt exist");
            return false;
        }
        if(!userInfo.get(username).getPassword().equals(password)){
            Log.e("Password","Wrong password");
            return false;
        }
        return true;
    }




}
