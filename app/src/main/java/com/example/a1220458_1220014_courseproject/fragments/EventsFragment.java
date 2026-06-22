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

import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.adapters.EventAdapter;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;
import com.example.a1220458_1220014_courseproject.models.Event;

import java.util.ArrayList;


public class EventsFragment extends Fragment {


    RecyclerView recyclerView;

    TextView emptyText;

    DatabaseHelper databaseHelper;

    ArrayList<Event> allEvents;

    EventAdapter adapter;

    SearchView searchView;

    Spinner categorySpinner;


    String searchText = "";

    String selectedCategory = "All";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        View view =
                inflater.inflate(
                        R.layout.fragment_events,
                        container,
                        false);



        recyclerView =
                view.findViewById(
                        R.id.eventsRecyclerView);


        emptyText =
                view.findViewById(
                        R.id.emptyText);



        searchView =
                view.findViewById(
                        R.id.searchView);



        categorySpinner =
                view.findViewById(
                        R.id.categorySpinner);



        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext())
        );



        databaseHelper =
                new DatabaseHelper(getContext());



        loadEvents();



        String[] categories = {

                "All",
                "Technology",
                "Competition",
                "Career",
                "Academic",
                "Business",
                "Ceremony"

        };



        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(
                        getContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        categories
                );


        categorySpinner.setAdapter(spinnerAdapter);



        categorySpinner.setOnItemSelectedListener(
                new android.widget.AdapterView.OnItemSelectedListener() {


                    @Override
                    public void onItemSelected(
                            android.widget.AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {


                        selectedCategory =
                                categories[position];


                        updateEvents();


                    }


                    @Override
                    public void onNothingSelected(
                            android.widget.AdapterView<?> parent) {

                    }

                });



        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {


                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        return false;

                    }



                    @Override
                    public boolean onQueryTextChange(String newText) {


                        searchText = newText;


                        updateEvents();


                        return true;

                    }

                });



        return view;

    }




    private void loadEvents(){


        allEvents = new ArrayList<>();


        Cursor cursor =
                databaseHelper.getAllEvents();



        System.out.println(
                "EVENTS FROM DATABASE = "
                        + cursor.getCount()
        );



        while(cursor.moveToNext()){



            String name =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("title")
                    );


            System.out.println(
                    "EVENT NAME = " + name
            );



            Event event =
                    new Event(


                            cursor.getInt(
                                    cursor.getColumnIndexOrThrow("id")
                            ),


                            name,


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

                    );



            allEvents.add(event);


        }



        cursor.close();



        System.out.println(
                "TOTAL EVENTS LOADED = "
                        + allEvents.size()
        );



        adapter =
                new EventAdapter(
                        new ArrayList<>(allEvents)
                );


        recyclerView.setAdapter(adapter);


    }






    private void updateEvents(){



        ArrayList<Event> filtered =
                new ArrayList<>();



        for(Event event : allEvents){



            boolean search =
                    event.getTitle()
                            .toLowerCase()
                            .contains(
                                    searchText.toLowerCase()
                            );



            boolean category =
                    selectedCategory.equals("All")
                            ||
                            event.getCategory()
                                    .equals(selectedCategory);



            if(search && category){

                filtered.add(event);

            }


        }



        adapter.updateList(filtered);



        if(filtered.isEmpty()){

            emptyText.setVisibility(View.VISIBLE);

        }else{

            emptyText.setVisibility(View.GONE);

        }


    }



}