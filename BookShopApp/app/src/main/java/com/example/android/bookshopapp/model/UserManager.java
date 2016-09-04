package com.example.android.bookshopapp.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Aydin on 29.8.2016 г..
 */
public class UserManager {
    private static UserManager ourInstance;
    private HashMap<String, User> userInfo;

    public static UserManager getInstance(Activity activity) {
        if(ourInstance == null){
            ourInstance = new UserManager(activity);
        }
        return ourInstance;
    }


    private UserManager(Activity activity) {

        userInfo = new HashMap<>();
        String json = activity.getSharedPreferences("OLX", Context.MODE_PRIVATE).getString("userInfo", "no users");
        Log.e("LOADED USERS", json);

        try {
            JSONArray arr = new JSONArray(json);
            for(int i = 0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                User user = new User(obj.getString("username"),
                        obj.getString("password"),
                        obj.getString("name"),
                        obj.getString("email"),
                         obj.getString("address"));
                userInfo.put(user.getUsername(), user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public boolean existsUser(String username) {
        return userInfo.containsKey(username);
    }

    public void userRegister(Activity activity, String username,String name, String pass1, String email, String addr) {
        User user = new User(username, name,pass1, email,addr);
        userInfo.put(username, user);


        SharedPreferences prefs = activity.getSharedPreferences("OLX",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String key = "userInfo";
        String value = "...";//JSON format containing all users from the collection
        JSONArray jsonUsers = new JSONArray();
        try {
            for (User u : userInfo.values()) {
                JSONObject jobj = new JSONObject();
                jobj.put("username", u.getUsername());
                jobj.put("name",u.getName());
                jobj.put("password", u.getPassword());
                jobj.put("email", u.getEmail());
                jobj.put("address", u.getAddress());
                jsonUsers.put(jobj);
            }
        }
        catch(JSONException e){
            Log.e("JSON", e.getMessage());
        }
        value = jsonUsers.toString();
        Log.e("JSON", value);
        editor.putString(key, value);
        editor.commit();
    }

    public boolean isLogin(String username, String password) {
        if (!existsUser(username)){
            Log.e("F", "user does not exist in map");
            return false;
        }
        if(!userInfo.get(username).getPassword().equals(password)){
            Log.e("F","user pass is wrong");
            return false;
        }
        return true;
    }

    public final static boolean isValidEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }
}
