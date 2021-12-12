package com.anu.registrationapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.anu.registrationapp.databinding.ItemCaptchaBinding;
import com.anu.registrationapp.models.Captcha;
import java.util.List;

public class CaptchaAdapter extends RecyclerView.Adapter<CaptchaAdapter.CaptchaHolder> {

    private final List<Captcha> captchaList;
    private final AdapterView.OnItemClickListener onItemClickListener;

    public CaptchaAdapter(List<Captcha> captchaList, AdapterView.OnItemClickListener onItemClickListener) {
        this.captchaList = captchaList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CaptchaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CaptchaHolder(ItemCaptchaBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CaptchaHolder holder, int position) {
        holder.binding.ivCaptcha.setImageResource(captchaList.get(position).getImageId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(null, holder.binding.ivClicked, holder.getAdapterPosition(), 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return captchaList.size();
    }

    public static class CaptchaHolder extends RecyclerView.ViewHolder {

        private final ItemCaptchaBinding binding;

        public CaptchaHolder(ItemCaptchaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
