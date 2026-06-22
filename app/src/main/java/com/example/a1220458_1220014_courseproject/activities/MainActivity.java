package com.example.a1220458_1220014_courseproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    ImageView imgLogo;
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.getWritableDatabase();

        imgLogo = findViewById(R.id.imgLogo);
        txtTitle = findViewById(R.id.txtTitle);
        Animation enterAnimation =
                AnimationUtils.loadAnimation(
                        this,
                        R.anim.logo_enter
                );

        imgLogo.startAnimation(enterAnimation);

        new Handler().postDelayed(() -> {

            Animation animation =
                    AnimationUtils.loadAnimation(
                            MainActivity.this,
                            R.anim.logo_animation
                    );

            imgLogo.startAnimation(animation);
            txtTitle.startAnimation(animation);

            new Handler().postDelayed(() -> {

                Intent intent = new Intent(
                        MainActivity.this,
                        IntroductionActivity.class
                );

                startActivity(intent);
                finish();

            }, 1200);

        }, 1800);
    }
}