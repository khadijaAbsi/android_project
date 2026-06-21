package com.example.a1220458_1220014_courseproject.fragments;

import android.os.Bundle;
import android.text.TextUtils;
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

    EditText etAdminEmail;
    EditText etAdminPassword;

    Button btnAddAdmin;

    DatabaseHelper databaseHelper;

    public AddAdminFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
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

        databaseHelper =
                new DatabaseHelper(requireContext());

        btnAddAdmin.setOnClickListener(v -> {

            String email =
                    etAdminEmail.getText().toString().trim();

            String password =
                    etAdminPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {

                etAdminEmail.setError("Enter Email");
                return;
            }

            if (TextUtils.isEmpty(password)) {

                etAdminPassword.setError("Enter Password");
                return;
            }

            boolean result =
                    databaseHelper.insertAdmin(
                            email,
                            password
                    );

            if (result) {

                Toast.makeText(
                        requireContext(),
                        "Admin Added Successfully",
                        Toast.LENGTH_SHORT
                ).show();

                etAdminEmail.setText("");
                etAdminPassword.setText("");

            } else {

                Toast.makeText(
                        requireContext(),
                        "Failed Or Admin Already Exists",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });

        return view;
    }
}