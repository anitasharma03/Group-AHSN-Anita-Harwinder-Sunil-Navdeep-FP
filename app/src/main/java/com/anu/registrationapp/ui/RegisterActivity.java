package com.anu.registrationapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.anu.registrationapp.database.SqLiteHelper;
import com.anu.registrationapp.databinding.ActivityRegisterBinding;
import com.anu.registrationapp.models.User;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    public static final String USER_INFO = "USER_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        verifyUserInputs();
    }

    private void verifyUserInputs() {
        binding.saveBtn.setOnClickListener(view -> {
            String name = binding.etName.getText().toString().trim();
            String email = binding.etEmail.getText().toString();
            String phoneNumber = binding.etPhoneNumber.getText().toString();
            SqLiteHelper sqLiteHelper = new SqLiteHelper(RegisterActivity.this);
            if (name.isEmpty()) {
                makeSnackBar("Please enter your Name");
            } else if (email.isEmpty()) {
                makeSnackBar("Please enter your Email");
            } else if (phoneNumber.isEmpty()) {
                makeSnackBar("Please enter your Phone Number");
            } else if (sqLiteHelper.isUserAlreadyExist(email)) {
                makeSnackBar("User exists with this email already exists");
            } else {
                Intent intent = new Intent(this, CaptchaActivity.class);
                intent.putExtra(USER_INFO, new User(name, email, phoneNumber));
                startActivity(intent);
            }
        });
    }

    private void makeSnackBar(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }
}