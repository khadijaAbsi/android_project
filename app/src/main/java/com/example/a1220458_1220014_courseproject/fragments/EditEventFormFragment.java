package com.example.a1220458_1220014_courseproject.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;
import com.example.a1220458_1220014_courseproject.models.Event;



public class EditEventFormFragment extends Fragment {



    EditText title;
    EditText description;
    EditText category;
    EditText seats;


    Button update;



    DatabaseHelper db;


    Event event;




    public EditEventFormFragment(Event event){

        this.event = event;

    }




    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState){


        View view =
                inflater.inflate(
                        R.layout.fragment_edit_event_form,
                        container,
                        false
                );



        db =
                new DatabaseHelper(getContext());



        title =
                view.findViewById(R.id.editTitle);


        description =
                view.findViewById(R.id.editDescription);


        category =
                view.findViewById(R.id.editCategory);


        seats =
                view.findViewById(R.id.editSeats);



        update =
                view.findViewById(R.id.updateEventButton);





        title.setText(event.getTitle());

        description.setText(event.getDescription());

        category.setText(event.getCategory());

        seats.setText(
                String.valueOf(event.getSeats())
        );





        update.setOnClickListener(v -> {



            boolean done =
                    db.updateEvent(

                            event.getId(),

                            title.getText().toString(),

                            description.getText().toString(),

                            category.getText().toString(),

                            event.getDate(),

                            event.getTime(),

                            event.getLocation(),

                            Integer.parseInt(
                                    seats.getText().toString()
                            )

                    );



            if(done){


                requireActivity()
                        .getSupportFragmentManager()
                        .popBackStack();


            }



        });



        return view;

    }

}