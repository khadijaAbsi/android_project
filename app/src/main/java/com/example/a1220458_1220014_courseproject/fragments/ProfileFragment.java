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
import android.widget.TextView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;

import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;

public class ProfileFragment extends Fragment {

    TextView tvProfileEmail;
    EditText etProfileFirstName;
    EditText etProfileLastName;
    EditText etProfilePhone;
    TextView tvProfileGender;
    TextView tvProfileMajor;

    EditText etNewPassword;
    EditText etConfirmPassword;

    Button btnUpdateProfile;

    DatabaseHelper databaseHelper;
    ImageView imgProfile;
    Button btnChooseImage;

    private static final int PICK_IMAGE_REQUEST = 1;

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

        tvProfileEmail = view.findViewById(R.id.tvProfileEmail);
        etProfileFirstName = view.findViewById(R.id.etProfileFirstName);
        etProfileLastName = view.findViewById(R.id.etProfileLastName);
        etProfilePhone = view.findViewById(R.id.etProfilePhone);
        tvProfileGender = view.findViewById(R.id.tvProfileGender);
        tvProfileMajor = view.findViewById(R.id.tvProfileMajor);

        etNewPassword = view.findViewById(R.id.etNewPassword);
        etConfirmPassword = view.findViewById(R.id.etConfirmPassword);

        btnUpdateProfile = view.findViewById(R.id.btnUpdateProfile);
        imgProfile = view.findViewById(R.id.imgProfile);

        btnChooseImage = view.findViewById(R.id.btnChooseImage);

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

            tvProfileEmail.setText(
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

            tvProfileGender.setText(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("gender")
                    )
            );


            tvProfileMajor.setText(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("major")
                    )
            );
        }

        cursor.close();

        btnUpdateProfile.setOnClickListener(v -> {

            String firstName =
                    etProfileFirstName.getText().toString().trim();

            String lastName =
                    etProfileLastName.getText().toString().trim();

            String phone =
                    etProfilePhone.getText().toString().trim();

            String newPassword =
                    etNewPassword.getText().toString().trim();

            String confirmPassword =
                    etConfirmPassword.getText().toString().trim();

            if (firstName.length() < 3) {

                etProfileFirstName.setError(
                        "Minimum 3 characters"
                );

                return;
            }

            if (lastName.length() < 3) {

                etProfileLastName.setError(
                        "Minimum 3 characters"
                );

                return;
            }

            if (phone.isEmpty()) {

                etProfilePhone.setError(
                        "Enter Phone Number"
                );

                return;
            }

            if (!newPassword.isEmpty()) {

                if (newPassword.length() < 6) {

                    etNewPassword.setError(
                            "Password must be at least 6 characters"
                    );

                    return;
                }

                if (!newPassword.matches(".*[A-Za-z].*")) {

                    etNewPassword.setError(
                            "Password must contain a letter"
                    );

                    return;
                }

                if (!newPassword.matches(".*\\d.*")) {

                    etNewPassword.setError(
                            "Password must contain a number"
                    );

                    return;
                }

                if (!newPassword.equals(confirmPassword)) {

                    etConfirmPassword.setError(
                            "Passwords do not match"
                    );

                    return;
                }
            }

            boolean updated =
                    databaseHelper.updateUser(
                            email,
                            firstName,
                            lastName,
                            phone
                    );

            if (updated) {

                if (!newPassword.isEmpty()) {

                    databaseHelper.updatePassword(
                            email,
                            newPassword
                    );
                }

                Toast.makeText(
                        requireContext(),
                        "Profile Updated Successfully",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                Toast.makeText(
                        requireContext(),
                        "Update Failed",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });
        btnChooseImage.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_PICK
            );

            intent.setType("image/*");

            startActivityForResult(
                    intent,
                    PICK_IMAGE_REQUEST
            );

        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data) {

        super.onActivityResult(
                requestCode,
                resultCode,
                data
        );

        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == getActivity().RESULT_OK
                && data != null
                && data.getData() != null) {

            Uri imageUri = data.getData();

            imgProfile.setImageURI(imageUri);
        }
    }
}