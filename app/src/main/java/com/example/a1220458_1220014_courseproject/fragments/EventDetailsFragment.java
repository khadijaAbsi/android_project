package com.example.a1220458_1220014_courseproject.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;
import android.widget.ImageView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class EventDetailsFragment extends Fragment {
    TextView availableSeats;
    TextView reservedSeats;
    DatabaseHelper databaseHelper;
    int eventId;

    TextView title;
    TextView description;
    TextView category;
    TextView date;
    TextView location;
    ImageView image;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(
                R.layout.fragment_event_details,
                container,
                false);
        availableSeats =
                view.findViewById(R.id.availableSeats);

        reservedSeats =
                view.findViewById(R.id.reservedSeats);

        databaseHelper =
                new DatabaseHelper(getContext());


        title = view.findViewById(R.id.detailTitle);
        description = view.findViewById(R.id.detailDescription);
        category = view.findViewById(R.id.detailCategory);
        date = view.findViewById(R.id.detailDate);
        location = view.findViewById(R.id.detailLocation);
        image = view.findViewById(R.id.detailImage);



        Bundle bundle = getArguments();


        if(bundle != null){

            eventId =
                    bundle.getInt("eventId");

            title.setText(bundle.getString("title"));

            description.setText(bundle.getString("description"));

            category.setText(bundle.getString("category"));

            date.setText(bundle.getString("date"));

            location.setText(bundle.getString("location"));
            String imageUrl = bundle.getString("image");


            Glide.with(requireContext())
                    .load(imageUrl)
                    .into(image);


            int available =
                    databaseHelper.getEventSeats(eventId);


            int reserved =
                    databaseHelper.getReservedSeats(eventId);


            availableSeats.setText(
                    "Available Seats: " + available
            );


            reservedSeats.setText(
                    "Reserved Seats: " + reserved
            );

        }


        return view;

    }


}