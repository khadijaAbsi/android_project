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
import com.example.a1220458_1220014_courseproject.utils.ReservationManager;



public class ReservationsFragment extends Fragment {



    RecyclerView recyclerView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(
                R.layout.fragment_reservations,
                container,
                false);



        recyclerView =
                view.findViewById(R.id.reservationsRecyclerView);



        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));



        EventAdapter adapter =
                new EventAdapter(
                        ReservationManager.getReservations()
                );


        recyclerView.setAdapter(adapter);



        return view;

    }

}