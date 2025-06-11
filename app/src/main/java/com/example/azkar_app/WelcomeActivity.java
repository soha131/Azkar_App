package com.example.azkar_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(view -> {
            Intent intent = new Intent(WelcomeActivity.this, MenuActivity.class);
            startActivity(intent);
        });
    }
}
