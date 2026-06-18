package com.example.a1220458_1220014_courseproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1220458_1220014_courseproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    IntroductionActivity.class
            );

            startActivity(intent);
            finish();

        }, 3000);

    }
}