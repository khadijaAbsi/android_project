package com.example.a1220458_1220014_courseproject.utils;


import com.example.a1220458_1220014_courseproject.models.Event;

import java.util.ArrayList;


public class ReservationManager {


    public static ArrayList<Event> reservations = new ArrayList<>();


    public static void addReservation(Event event){


        if(!reservations.contains(event)){

            reservations.add(event);

        }

    }


    public static ArrayList<Event> getReservations(){

        return reservations;

    }

}