package com.example.a1220458_1220014_courseproject.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ProfileFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;

    TextView tvProfileEmail;
    EditText etProfileFirstName;
    EditText etProfileLastName;
    EditText etProfilePhone;
    TextView tvProfileGender;
    TextView tvProfileMajor;
    EditText etNewPassword;
    EditText etConfirmPassword;
    Button btnUpdateProfile;
    Button btnChooseImage;
    ImageView imgProfile;

    DatabaseHelper databaseHelper;

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
        btnChooseImage = view.findViewById(R.id.btnChooseImage);
        imgProfile = view.findViewById(R.id.imgProfile);

        databaseHelper = new DatabaseHelper(requireContext());

        SharedPreferences preferences =
                requireActivity().getSharedPreferences(
                        "LoginPrefs",
                        Activity.MODE_PRIVATE
                );

        String email =
                preferences.getString(
                        "current_user_email",
                        ""
                );

        Cursor cursor = databaseHelper.getUserByEmail(email);

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

        loadProfileImage(email);

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
                etProfileFirstName.setError("Minimum 3 characters");
                return;
            }

            if (lastName.length() < 3) {
                etProfileLastName.setError("Minimum 3 characters");
                return;
            }

            if (phone.isEmpty()) {
                etProfilePhone.setError("Enter Phone Number");
                return;
            }

            if (!newPassword.isEmpty()) {

                if (newPassword.length() < 6) {
                    etNewPassword.setError("Password must be at least 6 characters");
                    return;
                }

                if (!newPassword.matches(".*[A-Za-z].*")) {
                    etNewPassword.setError("Password must contain a letter");
                    return;
                }

                if (!newPassword.matches(".*\\d.*")) {
                    etNewPassword.setError("Password must contain a number");
                    return;
                }

                if (!newPassword.equals(confirmPassword)) {
                    etConfirmPassword.setError("Passwords do not match");
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
                    databaseHelper.updatePassword(email, newPassword);
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

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST);

        });

        return view;
    }

    private void loadProfileImage(String email) {
        new Thread(() -> {
            try {
                String imageString = databaseHelper.getProfileImage(email);

                if (imageString == null || imageString.isEmpty()) return;

                byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;

                Bitmap bitmap = BitmapFactory.decodeByteArray(
                        imageBytes, 0, imageBytes.length, options);

                if (bitmap == null) return;

                // ارجع للـ main thread عشان تحدّث الـ UI
                requireActivity().runOnUiThread(() ->
                        imgProfile.setImageBitmap(bitmap)
                );

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == Activity.RESULT_OK
                && data != null
                && data.getData() != null) {

            Uri imageUri = data.getData();

            SharedPreferences preferences =
                    requireActivity().getSharedPreferences(
                            "LoginPrefs",
                            Activity.MODE_PRIVATE
                    );

            String email =
                    preferences.getString(
                            "current_user_email",
                            ""
                    );

            try (InputStream inputStream =
                         requireContext()
                                 .getContentResolver()
                                 .openInputStream(imageUri)) {

                if (inputStream == null) {
                    Toast.makeText(
                            requireContext(),
                            "Failed to open image",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                Bitmap originalBitmap =
                        BitmapFactory.decodeStream(inputStream);

                if (originalBitmap == null) {
                    Toast.makeText(
                            requireContext(),
                            "Invalid image",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                Bitmap scaledBitmap =
                        Bitmap.createScaledBitmap(
                                originalBitmap,
                                300,
                                300,
                                true
                        );

                ByteArrayOutputStream stream =
                        new ByteArrayOutputStream();

                scaledBitmap.compress(
                        Bitmap.CompressFormat.JPEG,
                        80,
                        stream
                );

                String imageString =
                        Base64.encodeToString(
                                stream.toByteArray(),
                                Base64.NO_WRAP
                        );

                databaseHelper.updateProfileImage(
                        email,
                        imageString
                );

                imgProfile.setImageBitmap(scaledBitmap);

                Toast.makeText(
                        requireContext(),
                        "Image Saved",
                        Toast.LENGTH_SHORT
                ).show();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(
                        requireContext(),
                        "Failed to save image",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }
}