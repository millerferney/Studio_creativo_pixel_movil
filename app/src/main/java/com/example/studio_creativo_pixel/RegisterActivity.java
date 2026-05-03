package com.example.studio_creativo_pixel;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView tvLoginLink = findViewById(R.id.tv_login_link);
        tvLoginLink.setOnClickListener(v -> finish());

        findViewById(R.id.btn_register).setOnClickListener(v -> {
            // Simulate registration and return
            finish();
        });
    }
}