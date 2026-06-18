package com.example.a1220458_1220014_courseproject.fragments;

import android.content.SharedPreferences;
import android.database.Cursor;
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

public class ProfileFragment extends Fragment {

    EditText etProfileEmail;
    EditText etProfileFirstName;
    EditText etProfileLastName;
    EditText etProfilePhone;
    EditText etProfileGender;
    EditText etProfileMajor;

    DatabaseHelper databaseHelper;
    Button btnUpdateProfile;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_profile,
                container,
                false
        );

        etProfileEmail = view.findViewById(R.id.etProfileEmail);
        etProfileFirstName = view.findViewById(R.id.etProfileFirstName);
        etProfileLastName = view.findViewById(R.id.etProfileLastName);
        etProfilePhone = view.findViewById(R.id.etProfilePhone);
        etProfileGender = view.findViewById(R.id.etProfileGender);
        etProfileMajor = view.findViewById(R.id.etProfileMajor);
        btnUpdateProfile =
                view.findViewById(R.id.btnUpdateProfile);

        databaseHelper = new DatabaseHelper(requireContext());

        SharedPreferences preferences =
                requireActivity().getSharedPreferences(
                        "LoginPrefs",
                        requireActivity().MODE_PRIVATE
                );

        String email =
                preferences.getString(
                        "current_user_email",
                        ""
                );

        Cursor cursor =
                databaseHelper.getUserByEmail(email);

        if (cursor.moveToFirst()) {

            etProfileEmail.setText(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("email")
                    )
            );

            etProfileFirstName.setText(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("first_name")
                    )
            );

            etProfileLastName.setText(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("last_name")
                    )
            );

            etProfilePhone.setText(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("phone")
                    )
            );

            etProfileGender.setText(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("gender")
                    )
            );

            etProfileMajor.setText(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("major")
                    )
            );
        }

        cursor.close();
        btnUpdateProfile.setOnClickListener(v -> {

            String newEmail =
                    etProfileEmail.getText().toString().trim();

            String firstName =
                    etProfileFirstName.getText().toString().trim();

            String lastName =
                    etProfileLastName.getText().toString().trim();

            String phone =
                    etProfilePhone.getText().toString().trim();
            if(firstName.length() < 3){

                etProfileFirstName.setError(
                        "Minimum 3 characters"
                );

                return;
            }

            if(lastName.length() < 3){

                etProfileLastName.setError(
                        "Minimum 3 characters"
                );

                return;
            }

            if(phone.isEmpty()){

                etProfilePhone.setError(
                        "Enter Phone Number"
                );

                return;
            }
            if (!android.util.Patterns.EMAIL_ADDRESS
                    .matcher(newEmail)
                    .matches()) {

                etProfileEmail.setError("Invalid Email");
                return;
            }
            boolean updated =
                    databaseHelper.updateUser(
                            email,
                            newEmail,
                            firstName,
                            lastName,
                            phone
                    );

            if(updated){

                Toast.makeText(
                        requireContext(),
                        "Profile Updated Successfully",
                        Toast.LENGTH_SHORT
                ).show();

            }else{

                Toast.makeText(
                        requireContext(),
                        "Update Failed",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });

        return view;
    }
}