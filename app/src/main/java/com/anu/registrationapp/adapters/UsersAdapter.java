package com.anu.registrationapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.anu.registrationapp.R;
import com.anu.registrationapp.databinding.ItemUesrsBinding;
import com.anu.registrationapp.models.User;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersHolder> {

    private final List<User> userList;
    private final AdapterView.OnItemClickListener onItemClickListener;

    public UsersAdapter(List<User> userList, AdapterView.OnItemClickListener onItemClickListener) {
        this.userList = userList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UsersHolder(ItemUesrsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, int position) {

        if (position % 2 == 0) {
            holder.binding.ivUserProfile.setImageResource(R.drawable.ic_profile);
        } else {
            holder.binding.ivUserProfile.setImageResource(R.drawable.ic_male_profile);
        }

        User user = userList.get(position);
        holder.binding.tvName.setText(user.getUsername());
        holder.binding.tvEmailPhone.setText(user.getEmail() + "\n" + user.getPhoneNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(null, holder.itemView,
                        holder.getAdapterPosition(), 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UsersHolder extends RecyclerView.ViewHolder {

        private final ItemUesrsBinding binding;

        public UsersHolder(ItemUesrsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
