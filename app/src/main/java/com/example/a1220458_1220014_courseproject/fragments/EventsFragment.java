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


import java.util.ArrayList;



public class EventsFragment extends Fragment {



    RecyclerView recyclerView;

    ArrayList<Event> eventList;

    EventAdapter adapter;



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



        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));



        eventList = new ArrayList<>();


        // بيانات تجريبية مؤقتة

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
                "Programming Competition",
                "Java Android Contest",
                "Competition",
                "2026-06-15",
                "12:00 PM",
                "Lab 3",
                50,
                ""));



        adapter = new EventAdapter(eventList);


        recyclerView.setAdapter(adapter);



        return view;

    }

}