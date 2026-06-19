package com.example.a1220458_1220014_courseproject.fragments;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.database.Cursor;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.adapters.EventAdapter;
import com.example.a1220458_1220014_courseproject.models.Event;


import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;


import java.util.ArrayList;



public class EventsFragment extends Fragment {

    DatabaseHelper databaseHelper;

    RecyclerView recyclerView;


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



        View view = inflater.inflate(
                R.layout.fragment_events,
                container,
                false);



        recyclerView = view.findViewById(R.id.eventsRecyclerView);


        searchView = view.findViewById(R.id.searchView);


        categorySpinner = view.findViewById(R.id.categorySpinner);




        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));




        allEvents = new ArrayList<>();

        databaseHelper =
                new DatabaseHelper(getContext());


        Cursor cursor =
                databaseHelper.getAllEvents();



        while(cursor.moveToNext()){


            allEvents.add(
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


        adapter = new EventAdapter(new ArrayList<>(allEvents));


        recyclerView.setAdapter(adapter);




        String[] categories = {

                "All",
                "Technology",
                "Competition"

        };




        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(
                        getContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        categories);



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







    private void updateEvents(){



        ArrayList<Event> filteredList =
                new ArrayList<>();




        for(Event event : allEvents){



            boolean searchMatch =
                    event.getTitle()
                            .toLowerCase()
                            .contains(searchText.toLowerCase());




            boolean categoryMatch =
                    selectedCategory.equals("All")
                            ||
                            event.getCategory()
                                    .equals(selectedCategory);





            if(searchMatch && categoryMatch){


                filteredList.add(event);


            }


        }




        adapter.updateList(filteredList);



    }



}