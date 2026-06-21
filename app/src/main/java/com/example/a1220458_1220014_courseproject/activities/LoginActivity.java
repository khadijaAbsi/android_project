package com.example.a1220458_1220014_courseproject.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;


import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;
    EditText etUsername, etPassword;
    CheckBox cbRememberMe;

    DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        cbRememberMe = findViewById(R.id.cbRememberMe);

        sharedPreferences =
                getSharedPreferences("LoginPrefs", MODE_PRIVATE);

        String savedEmail =
                sharedPreferences.getString("email", "");

        boolean remember =
                sharedPreferences.getBoolean("remember", false);

        etUsername.setText(savedEmail);
        cbRememberMe.setChecked(remember);

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

                SharedPreferences.Editor editor =
                        sharedPreferences.edit();

                editor.putString("current_user_email", email);

                if (cbRememberMe.isChecked()) {

                    editor.putString("email", email);
                    editor.putBoolean("remember", true);

                } else {

                    editor.putBoolean("remember", false);

                }

                editor.apply();

                Toast.makeText(
                        this,
                        "Admin Login",
                        Toast.LENGTH_SHORT
                ).show();

              startActivity(
                        new Intent(
                                LoginActivity.this,
                                AdminActivity.class
                        )
                );

                finish();
                return;
            }

            // User Login
            if (databaseHelper.checkUser(email, password)) {


                int userId =
                        databaseHelper.getUserId(email);


                SharedPreferences.Editor editor =
                        sharedPreferences.edit();


                editor.putInt(
                        "user_id",
                        userId
                );


                if (cbRememberMe.isChecked()) {

                    editor.putString(
                            "email",
                            email
                    );

                    editor.putBoolean(
                            "remember",
                            true
                    );

                    editor.putString(
                            "current_user_email",
                            email
                    );

                } else {

                    editor.clear();

                }


                editor.apply();

                Toast.makeText(
                        this,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                ).show();

                startActivity(
                        new Intent(
                                LoginActivity.this,
                                HomeActivity.class
                        )
                );

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