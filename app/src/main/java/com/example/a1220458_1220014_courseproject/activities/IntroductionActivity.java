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
                            "https://mocki.io/v1/a29291b4-28a6-4b38-a907-bba321311299"
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