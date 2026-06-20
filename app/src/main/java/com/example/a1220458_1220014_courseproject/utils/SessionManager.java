package com.example.a1220458_1220014_courseproject.utils;


import android.content.Context;
import android.content.SharedPreferences;


public class SessionManager {


    private SharedPreferences preferences;


    public SessionManager(Context context){

        preferences =
                context.getSharedPreferences(
                        "session",
                        Context.MODE_PRIVATE
                );

    }



    public void saveUserId(int id){

        preferences.edit()
                .putInt("userId",id)
                .apply();

    }



    public int getUserId(){

        return preferences.getInt(
                "userId",
                -1
        );

    }



    public boolean isLoggedIn(){

        return getUserId() != -1;

    }



    public void logout(){

        preferences.edit()
                .clear()
                .apply();

    }


}