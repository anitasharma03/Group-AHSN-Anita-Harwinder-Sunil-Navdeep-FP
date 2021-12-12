package com.anu.registrationapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anu.registrationapp.R;
import com.anu.registrationapp.databinding.FragmentUserProfileBinding;
import com.anu.registrationapp.models.User;

public class UserProfileFragment extends Fragment {

    private FragmentUserProfileBinding binding;

    public UserProfileFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserProfileBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            User user = (User) bundle.getSerializable("user");
            int position = bundle.getInt("position");
            if (position % 2 == 0) {
                binding.ivUserProfile.setImageResource(R.drawable.ic_profile);
            } else {
                binding.ivUserProfile.setImageResource(R.drawable.ic_male_profile);
            }

            binding.tvName.setText(user.getUsername());
            binding.tvEmail.setText(user.getEmail());
            binding.tvPhone.setText(user.getPhoneNumber());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}