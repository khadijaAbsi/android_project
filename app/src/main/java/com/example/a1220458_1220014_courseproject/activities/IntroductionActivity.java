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
                    .execute("https://mocki.io/v1/e06e4140-75fe-4379-beb4-0ea7fefe109d"
                   // .execute("https://mocki.io/v1/b883542e-5b71-4300-8510-a8b4890f1485"

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
    public void openlogin(){

        Intent intent =
                new Intent(
                        IntroductionActivity.this,
                        LoginActivity.class
                );

        startActivity(intent);

    }
}