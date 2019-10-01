package com.example.analisishasilpertanian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.analisishasilpertanian.admin.MainAdminActivity;
import com.example.analisishasilpertanian.user.MainActivity;

public class MainLogin extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        Button button = findViewById(R.id.button4);
        Button button1 = findViewById(R.id.button5);

        button.setOnClickListener(view -> {
            intent = new Intent(this, MainAdminActivity.class);
            startActivity(intent);
        });

        button1.setOnClickListener(view -> {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}
