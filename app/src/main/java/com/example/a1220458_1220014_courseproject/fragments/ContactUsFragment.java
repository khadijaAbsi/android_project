package com.example.a1220458_1220014_courseproject.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.a1220458_1220014_courseproject.R;

public class ContactUsFragment extends Fragment {

    Button btnCall;
    Button btnEmail;
    Button btnLocation;

    public ContactUsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_contact_us,
                container,
                false
        );

        btnCall = view.findViewById(R.id.btnCall);
        btnEmail = view.findViewById(R.id.btnEmail);
        btnLocation = view.findViewById(R.id.btnLocation);

        btnCall.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_DIAL
            );

            intent.setData(
                    Uri.parse("tel:+970599123456")
            );

            startActivity(intent);
        });

        btnEmail.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_SENDTO
            );

            intent.setData(
                    Uri.parse("mailto:events@university.edu")
            );

            intent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "University Events"
            );

            startActivity(intent);
        });

        btnLocation.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                            "geo:0,0?q=Birzeit University"
                    )
            );

            startActivity(intent);
        });

        return view;
    }
}