package com.example.a1220458_1220014_courseproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.a1220458_1220014_courseproject.network.ConnectionAsyncTask;
import com.example.a1220458_1220014_courseproject.R;

public class IntroductionActivity extends AppCompatActivity {

    Button btnConnect, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        btnConnect = findViewById(R.id.btnConnect);
        btnHome = findViewById(R.id.btnHome);

        btnConnect.setOnClickListener(v -> {


            new ConnectionAsyncTask(this)
                    .execute("https://mocki.io/v1/76e0c2f6-0253-4272-b3e0-8158b474c372"
                    );
        });

        // Go To Main -> HomeActivity
        btnHome.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            IntroductionActivity.this,
                            HomeActivity.class);

            startActivity(intent);

        });
    }
    public void openHome(){


        Intent intent =
                new Intent(
                        IntroductionActivity.this,
                        HomeActivity.class
                );


        startActivity(intent);


    }
}