package com.example.a1220458_1220014_courseproject.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.a1220458_1220014_courseproject.R;



public class EventDetailsFragment extends Fragment {


    TextView title;
    TextView description;
    TextView category;
    TextView date;
    TextView location;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(
                R.layout.fragment_event_details,
                container,
                false);



        title = view.findViewById(R.id.detailTitle);
        description = view.findViewById(R.id.detailDescription);
        category = view.findViewById(R.id.detailCategory);
        date = view.findViewById(R.id.detailDate);
        location = view.findViewById(R.id.detailLocation);



        Bundle bundle = getArguments();


        if(bundle != null){

            title.setText(bundle.getString("title"));

            description.setText(bundle.getString("description"));

            category.setText(bundle.getString("category"));

            date.setText(bundle.getString("date"));

            location.setText(bundle.getString("location"));

        }


        return view;

    }


}