package com.example.a1220458_1220014_courseproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;

public class AddAdminFragment extends Fragment {

    EditText etAdminEmail, etAdminPassword;
    Button btnAddAdmin;

    DatabaseHelper db;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view =
                inflater.inflate(
                        R.layout.fragment_add_admin,
                        container,
                        false
                );

        etAdminEmail =
                view.findViewById(R.id.etAdminEmail);

        etAdminPassword =
                view.findViewById(R.id.etAdminPassword);

        btnAddAdmin =
                view.findViewById(R.id.btnAddAdmin);

        db = new DatabaseHelper(getContext());

        btnAddAdmin.setOnClickListener(v -> {

            boolean inserted =
                    db.insertAdmin(
                            etAdminEmail.getText().toString(),
                            etAdminPassword.getText().toString()
                    );

            if(inserted){

                Toast.makeText(
                        getContext(),
                        "Admin Added",
                        Toast.LENGTH_SHORT
                ).show();

            }else{

                Toast.makeText(
                        getContext(),
                        "Failed",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });

        return view;
    }
}