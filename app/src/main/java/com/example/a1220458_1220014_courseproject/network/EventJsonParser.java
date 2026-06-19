package com.example.a1220458_1220014_courseproject.network;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import com.example.a1220458_1220014_courseproject.models.Event;


public class EventJsonParser {


    public static ArrayList<Event> getObjectFromJson(String json){


        ArrayList<Event> list =
                new ArrayList<>();


        try{


            JSONArray array =
                    new JSONArray(json);



            for(int i=0;i<array.length();i++){


                JSONObject obj =
                        array.getJSONObject(i);



                Event event =
                        new Event(

                                obj.getInt("id"),

                                obj.getString("title"),

                                obj.getString("description"),

                                obj.getString("category"),

                                obj.getString("date"),

                                obj.getString("time"),

                                obj.getString("location"),

                                obj.getInt("seats"),

                                obj.getString("image")

                        );


                list.add(event);


            }



        }catch(Exception e){


            e.printStackTrace();


        }



        return list;


    }


}