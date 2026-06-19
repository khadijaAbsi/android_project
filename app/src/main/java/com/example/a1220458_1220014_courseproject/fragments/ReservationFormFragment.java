package com.example.a1220458_1220014_courseproject.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.a1220458_1220014_courseproject.R;



public class ReservationFormFragment extends Fragment {


    EditText quantity;

    Spinner reservationType;

    Button confirmButton;



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




        confirmButton.setOnClickListener(v -> {



            if(quantity.getText().toString().isEmpty()){


                Toast.makeText(
                        getContext(),
                        "Enter quantity",
                        Toast.LENGTH_SHORT
                ).show();


            }else{


                Toast.makeText(
                        getContext(),
                        "Reservation Confirmed",
                        Toast.LENGTH_SHORT
                ).show();


            }


        });




        return view;

    }


}