package com.example.a1220458_1220014_courseproject.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.adapters.ReservationAdapter;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;
import com.example.a1220458_1220014_courseproject.models.Reservation;


import java.util.ArrayList;

import com.example.a1220458_1220014_courseproject.models.Reservation;

public class ReservationsFragment extends Fragment {



    RecyclerView recyclerView;
    int userId;
    ArrayList<Reservation> reservations;
    ReservationAdapter adapter;

    DatabaseHelper databaseHelper;



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
                view.findViewById(
                        R.id.reservationsRecyclerView);



        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));



        databaseHelper =
                new DatabaseHelper(getContext());
        SharedPreferences prefs =
                getActivity().getSharedPreferences(
                        "LoginPrefs",
                        Context.MODE_PRIVATE
                );

        String email =
                prefs.getString(
                        "current_user_email",
                        ""
                );

        userId = databaseHelper.getUserId(email);

        Log.d("USER_ID", "userId = " + userId);
        Log.d("USER_EMAIL", "email = " + email);


        reservations = new ArrayList<>();

        Cursor cursor =
                databaseHelper.getUserReservations(userId);


        while(cursor.moveToNext()){

            String eventName =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("title")
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



            String eventDate =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("reservation_date")
                    );


            reservations.add(
                    new Reservation(
                            eventName,
                            eventDate,
                            quantity,
                            type,
                            status
                    )
            );


        }



        cursor.close();


        System.out.println("LIST SIZE = " + reservations.size());
        adapter =
                new ReservationAdapter(reservations);



        recyclerView.setAdapter(adapter);



        return view;


    }


}