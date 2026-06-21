package com.example.a1220458_1220014_courseproject.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;
import com.example.a1220458_1220014_courseproject.models.Event;



public class AddEventFragment extends Fragment {



    EditText title, description, category,
            date, time, location, seats;


    Button save;


    DatabaseHelper db;



    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState){


        View view =
                inflater.inflate(
                        R.layout.fragment_add_event,
                        container,
                        false
                );



        db =
                new DatabaseHelper(getContext());



        title =
                view.findViewById(R.id.eventTitle);


        description =
                view.findViewById(R.id.eventDescription);


        category =
                view.findViewById(R.id.eventCategory);


        date =
                view.findViewById(R.id.eventDate);


        time =
                view.findViewById(R.id.eventTime);


        location =
                view.findViewById(R.id.eventLocation);


        seats =
                view.findViewById(R.id.eventSeats);


        save =
                view.findViewById(R.id.saveEvent);



        save.setOnClickListener(v -> {



            Event event =
                    new Event(

                            0,

                            title.getText().toString(),

                            description.getText().toString(),

                            category.getText().toString(),

                            date.getText().toString(),

                            time.getText().toString(),

                            location.getText().toString(),

                            Integer.parseInt(
                                    seats.getText().toString()
                            ),

                            ""

                    );



            db.insertEvent(event);



            Toast.makeText(
                    getContext(),
                    "Event Added",
                    Toast.LENGTH_SHORT
            ).show();



            requireActivity()
                    .getSupportFragmentManager()
                    .popBackStack();



        });



        return view;


    }


}