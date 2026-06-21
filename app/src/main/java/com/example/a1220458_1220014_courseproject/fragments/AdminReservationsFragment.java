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
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;
import com.example.a1220458_1220014_courseproject.adapters.ReservationAdapter;
import com.example.a1220458_1220014_courseproject.models.Reservation;

import java.util.ArrayList;

public class AdminReservationsFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseHelper db;
    ArrayList<Reservation> reservations;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_admin_reservations,
                container,
                false
        );

        recyclerView =
                view.findViewById(
                        R.id.reservationsRecyclerView
                );

        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext())
        );

        db = new DatabaseHelper(getContext());

        reservations = new ArrayList<>();

        loadReservations();

        recyclerView.setAdapter(
                new ReservationAdapter(reservations)
        );

        return view;
    }


    private void loadReservations(){

        Cursor cursor =
                db.getReservations();

        while(cursor.moveToNext()){

            String title =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("title")
                    );

            String date =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("reservation_date")
                    );

            int quantity =
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow("quantity")
                    );

            String type =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("reservation_type")
                    );

            String status =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("status")
                    );


            reservations.add(
                    new Reservation(
                            title,
                            date,
                            quantity,
                            type,
                            status
                    )
            );

        }


        cursor.close();

    }
}