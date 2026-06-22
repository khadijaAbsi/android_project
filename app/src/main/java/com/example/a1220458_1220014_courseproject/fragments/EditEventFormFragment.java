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

    EditText date;
    EditText time;
    EditText location;
    EditText image;
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
        date = view.findViewById(R.id.editDate);

        time = view.findViewById(R.id.editTime);

        location = view.findViewById(R.id.editLocation);

        image = view.findViewById(R.id.editImage);


        seats =
                view.findViewById(R.id.editSeats);



        update =
                view.findViewById(R.id.updateEventButton);





        title.setText(event.getTitle());

        description.setText(event.getDescription());

        category.setText(event.getCategory());
        date.setText(event.getDate());

        time.setText(event.getTime());

        location.setText(event.getLocation());

        image.setText(event.getImage());

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

                            date.getText().toString(),

                            time.getText().toString(),

                            location.getText().toString(),

                            Integer.parseInt(
                                    seats.getText().toString()
                            ),

                            image.getText().toString()

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