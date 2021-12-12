package com.anu.registrationapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.anu.registrationapp.R;
import com.anu.registrationapp.adapters.UsersAdapter;
import com.anu.registrationapp.database.SqLiteHelper;
import com.anu.registrationapp.databinding.FragmentAllUsersBinding;
import com.anu.registrationapp.models.User;

import java.util.List;

public class AllUsersFragment extends Fragment {

    private FragmentAllUsersBinding binding;

    public AllUsersFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllUsersBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUsersRV();
        binding.addUerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUsersRV() {
        SqLiteHelper sqLiteHelper = new SqLiteHelper(getContext());
        List<User> users = sqLiteHelper.getAllUsers();

        UsersAdapter usersAdapter = new UsersAdapter(users, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", users.get(i));
                bundle.putInt("position", i);

                UserProfileFragment userProfileFragment = new UserProfileFragment();
                userProfileFragment.setArguments(bundle);

                assert getActivity() != null;
                if (AllUsersActivity.isTwoPaneMode) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(AllUsersActivity.container, userProfileFragment)
                            .addToBackStack(null)
                            .commitAllowingStateLoss();
                } else {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_holder, userProfileFragment)
                            .addToBackStack(null)
                            .commitAllowingStateLoss();
                }
            }
        });
        binding.rvUsers.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvUsers.setAdapter(usersAdapter);
        assert getContext() != null;
        binding.rvUsers.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}