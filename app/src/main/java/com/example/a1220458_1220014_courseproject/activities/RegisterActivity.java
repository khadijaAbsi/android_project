package com.example.a1220458_1220014_courseproject.activities;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail, etFirstName, etLastName,
            etPassword, etConfirmPassword, etPhone;

    Spinner spGender, spMajor;
    Button btnRegister;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);

        etEmail = findViewById(R.id.etEmail);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etPhone = findViewById(R.id.etPhone);

        spGender = findViewById(R.id.spGender);
        spMajor = findViewById(R.id.spMajor);

        btnRegister = findViewById(R.id.btnRegister);

        String[] genders = {"Male", "Female"};

        ArrayAdapter<String> genderAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        genders
                );

        genderAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spGender.setAdapter(genderAdapter);

        String[] majors = {
                "Computer Engineering",
                "Electrical Engineering",
                "Civil Engineering",
                "Architecture",
                "Business"
        };

        ArrayAdapter<String> majorAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        majors
                );

        majorAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spMajor.setAdapter(majorAdapter);

        btnRegister.setOnClickListener(v -> validateInputs());
    }

    private void validateInputs() {

        String email = etEmail.getText().toString().trim();
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Invalid Email");
            return;
        }

        if (firstName.length() < 3) {
            etFirstName.setError("Minimum 3 characters");
            return;
        }

        if (lastName.length() < 3) {
            etLastName.setError("Minimum 3 characters");
            return;
        }

        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            return;
        }

        if (!password.matches(".*[A-Za-z].*")) {
            etPassword.setError("Password must contain a letter");
            return;
        }

        if (!password.matches(".*\\d.*")) {
            etPassword.setError("Password must contain a number");
            return;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match");
            return;
        }

        if (phone.isEmpty()) {
            etPhone.setError("Enter Phone Number");
            return;
        }

        String gender = spGender.getSelectedItem().toString();
        String major = spMajor.getSelectedItem().toString();

        boolean inserted = databaseHelper.insertUser(
                email,
                firstName,
                lastName,
                password,
                gender,
                major,
                phone
        );

        if (inserted) {



            Toast.makeText(
                    this,
                    "Registration Successful",
                    Toast.LENGTH_SHORT
            ).show();

            finish();

        } else {

            Toast.makeText(
                    this,
                    "Email already exists",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}