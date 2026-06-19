package com.example.a1220458_1220014_courseproject.fragments;


import android.database.Cursor;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.adapters.EventAdapter;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;
import com.example.a1220458_1220014_courseproject.models.Event;


import java.util.ArrayList;



public class SpecialFragment extends Fragment {


    RecyclerView featuredRecyclerView;

    RecyclerView popularRecyclerView;

    RecyclerView recommendedRecyclerView;


    DatabaseHelper databaseHelper;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        View view =
                inflater.inflate(
                        R.layout.fragment_special,
                        container,
                        false);

        featuredRecyclerView =
                view.findViewById(
                        R.id.featuredRecyclerView);
        featuredRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));

        popularRecyclerView =
                view.findViewById(
                        R.id.popularRecyclerView);



        recommendedRecyclerView =
                view.findViewById(
                        R.id.recommendedRecyclerView);



        popularRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));



        recommendedRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));



        databaseHelper =
                new DatabaseHelper(getContext());

        ArrayList<Event> featured =
                getEvents(
                        databaseHelper.getFeaturedEvents()
                );

        ArrayList<Event> popular =
                getEvents(
                        databaseHelper.getPopularEvents()
                );



        ArrayList<Event> recommended =
                getEvents(
                        databaseHelper.getRecommendedEvents()
                );

        featuredRecyclerView.setAdapter(
                new EventAdapter(featured)
        );

        popularRecyclerView.setAdapter(
                new EventAdapter(popular)
        );



        recommendedRecyclerView.setAdapter(
                new EventAdapter(recommended)
        );



        return view;

    }





    private ArrayList<Event> getEvents(Cursor cursor){


        ArrayList<Event> list =
                new ArrayList<>();



        while(cursor.moveToNext()){



            list.add(
                    new Event(

                            cursor.getInt(
                                    cursor.getColumnIndexOrThrow("id")
                            ),

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("title")
                            ),

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("description")
                            ),

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("category")
                            ),

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("date")
                            ),

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("time")
                            ),

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("location")
                            ),

                            cursor.getInt(
                                    cursor.getColumnIndexOrThrow("seats")
                            ),

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("image")
                            )

                    )
            );

        }


        cursor.close();


        return list;

    }


}