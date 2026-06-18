package com.example.a1220458_1220014_courseproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;
    EditText etUsername, etPassword;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnLogin.setOnClickListener(v -> {

            String email =
                    etUsername.getText().toString().trim();

            String password =
                    etPassword.getText().toString().trim();

            if (email.isEmpty()) {
                etUsername.setError("Enter Email");
                return;
            }

            if (password.isEmpty()) {
                etPassword.setError("Enter Password");
                return;
            }

            // Admin Login
            if (databaseHelper.checkAdmin(email, password)) {

                Toast.makeText(
                        this,
                        "Admin Login",
                        Toast.LENGTH_SHORT
                ).show();

                Intent intent =
                        new Intent(
                                LoginActivity.this,
                                HomeActivity.class
                        );

                startActivity(intent);
                finish();
                return;
            }

            // User Login
            if (databaseHelper.checkUser(email, password)) {

                Toast.makeText(
                        this,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                ).show();

                Intent intent =
                        new Intent(
                                LoginActivity.this,
                                HomeActivity.class
                        );

                startActivity(intent);
                finish();

            } else {

                Toast.makeText(
                        this,
                        "Invalid Email or Password",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        btnRegister.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            LoginActivity.this,
                            RegisterActivity.class
                    );

            startActivity(intent);

        });
    }
}