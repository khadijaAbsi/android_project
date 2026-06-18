package com.example.a1220458_1220014_courseproject.fragments;


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
import com.example.a1220458_1220014_courseproject.models.Event;
import android.widget.SearchView;

import java.util.ArrayList;



public class EventsFragment extends Fragment {



    RecyclerView recyclerView;

    ArrayList<Event> eventList;

    EventAdapter adapter;
    SearchView searchView;


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
                view.findViewById(R.id.eventsRecyclerView);
        searchView =
                view.findViewById(R.id.searchView);


        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));



        eventList = new ArrayList<>();



        eventList.add(new Event(
                1,
                "AI Workshop",
                "Introduction to AI",
                "Technology",
                "2026-06-10",
                "10:00 AM",
                "Engineering Hall",
                80,
                ""));



        eventList.add(new Event(
                2,
                "problem solving Competition",
                "C programing",
                "Competition",
                "2026-06-19",
                "12:00 PM",
                "Ramallah Birzeit",
                500,
                ""));



        adapter = new EventAdapter(eventList);


        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {


                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        return false;

                    }


                    @Override
                    public boolean onQueryTextChange(String newText) {

                        adapter.filterList(newText);

                        return true;

                    }

                });

        return view;

    }

}