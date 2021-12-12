package com.anu.registrationapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.anu.registrationapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public static final String FIRST_TIME = "FIRST_TIME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (isFirstTime()) {
            binding.addUerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PreferenceManager.getDefaultSharedPreferences(MainActivity.this)
                            .edit().putBoolean(FIRST_TIME, false).apply();
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            Intent intent = new Intent(MainActivity.this, AllUsersActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private Boolean isFirstTime() {
        return PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean(FIRST_TIME, true);
    }
}