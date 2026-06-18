package com.example.a1220458_1220014_courseproject.utils;


import com.example.a1220458_1220014_courseproject.models.Event;

import java.util.ArrayList;


public class FavoriteManager {


    public static ArrayList<Event> favorites = new ArrayList<>();



    public static void addFavorite(Event event){


        if(!favorites.contains(event)){

            favorites.add(event);

        }

    }



    public static ArrayList<Event> getFavorites(){

        return favorites;

    }


}