package com.anu.registrationapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.anu.registrationapp.R;
import com.anu.registrationapp.databinding.ActivityAllUsersBinding;

public class AllUsersActivity extends AppCompatActivity {

    private ActivityAllUsersBinding binding;
    public static Boolean isTwoPaneMode = false;
    @SuppressLint("StaticFieldLeak")
    public static int container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_holder, new AllUsersFragment())
                .commitAllowingStateLoss();

        if (binding.fragmentContainer2 != null) {
            // two pane mode
            isTwoPaneMode = true;
            container = binding.fragmentContainer2.getId();
        }

    }
}