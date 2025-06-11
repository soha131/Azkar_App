package com.example.azkar_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button btnMorningAzkar, btnEveningAzkar, btnDua, btnZakr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnMorningAzkar = findViewById(R.id.btnMorning);
        btnEveningAzkar = findViewById(R.id.btnEvening);
        btnDua = findViewById(R.id.btnDua);
        btnZakr = findViewById(R.id.btnZkr);

        btnMorningAzkar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MorningAzkarActivity.class);
                startActivity(intent);
            }
        });

        btnEveningAzkar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, EveningAzkarActivity.class);
                startActivity(intent);
            }
        });

        btnDua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DuaActivity.class);
                startActivity(intent);
            }
        });

        btnZakr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ZakrActivity.class);
                startActivity(intent);
            }
        });
    }
}
