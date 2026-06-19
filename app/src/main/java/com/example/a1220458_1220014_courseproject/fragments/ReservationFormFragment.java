package com.example.a1220458_1220014_courseproject.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;



public class ReservationFormFragment extends Fragment {



    EditText quantity;

    Spinner reservationType;

    Button confirmButton;

    DatabaseHelper databaseHelper;

    int eventId;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(
                R.layout.fragment_reservation_form,
                container,
                false);



        quantity = view.findViewById(R.id.quantity);

        reservationType = view.findViewById(R.id.reservationType);

        confirmButton = view.findViewById(R.id.confirmReservation);



        databaseHelper = new DatabaseHelper(getContext());



        if(getArguments() != null){

            eventId =
                    getArguments().getInt("eventId");

        }




        String[] reservationTypes = {
                "Student",
                "Lecturer",
                "Guest",
                "VIP"
        };



        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(
                        getContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        reservationTypes
                );



        reservationType.setAdapter(spinnerAdapter);




        confirmButton.setOnClickListener(v -> {



            String count =
                    quantity.getText().toString();



            String type =
                    reservationType.getSelectedItem().toString();




            if(count.isEmpty()){



                Toast.makeText(
                        getContext(),
                        "Enter quantity",
                        Toast.LENGTH_SHORT
                ).show();



            }else{



                boolean saved =
                        databaseHelper.insertReservation(
                                1,
                                eventId,
                                Integer.parseInt(count),
                                type,
                                "Confirmed"
                        );



                if(saved){


                    Toast.makeText(
                            getContext(),
                            "Reservation Confirmed\n"
                                    + "Quantity: " + count
                                    + "\nType: " + type,
                            Toast.LENGTH_SHORT
                    ).show();


                }else{


                    Toast.makeText(
                            getContext(),
                            "Reservation failed",
                            Toast.LENGTH_SHORT
                    ).show();


                }



            }


        });




        return view;

    }


}