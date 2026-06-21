package com.example.a1220458_1220014_courseproject.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.adapters.AdminEventAdapter;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;
import com.example.a1220458_1220014_courseproject.models.Event;

import java.util.ArrayList;


public class AdminEventsFragment extends Fragment {


    RecyclerView recyclerView;

    DatabaseHelper databaseHelper;

    ArrayList<Event> eventList;


    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {


        View view =
                inflater.inflate(
                        R.layout.fragment_admin_events,
                        container,
                        false
                );


        recyclerView =
                view.findViewById(
                        R.id.adminEventsRecyclerView
                );
        view.findViewById(R.id.addEventButton)
                .setOnClickListener(v -> {

                    requireActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(
                                    R.id.fragmentContainer,
                                    new AddEventFragment()
                            )
                            .addToBackStack(null)
                            .commit();

                });

        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext())
        );


        databaseHelper =
                new DatabaseHelper(getContext());


        eventList =
                new ArrayList<>();


        loadEvents();


        AdminEventAdapter adapter =
                new AdminEventAdapter(
                        eventList
                );


        recyclerView.setAdapter(adapter);



        return view;

    }



    private void loadEvents(){


        Cursor cursor =
                databaseHelper.getAllEvents();


        while(cursor.moveToNext()){


            eventList.add(

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

    }


}