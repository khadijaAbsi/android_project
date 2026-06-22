package com.example.a1220458_1220014_courseproject.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.network.ConnectionAsyncTask;

public class IntroductionActivity extends AppCompatActivity {

    Button btnConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        btnConnect = findViewById(R.id.btnConnect);

        btnConnect.setOnClickListener(v -> {

            new ConnectionAsyncTask(this)
                    .execute(
                            "https://mocki.io/v1/bd8f74ef-ed76-4e7c-8379-1ce654070bf8"
                    );

        });
    }

    public void openlogin() {

        startActivity(
                new android.content.Intent(
                        IntroductionActivity.this,
                        LoginActivity.class
                )
        );

        finish();
    }
}