package com.anu.registrationapp.ui;

import static com.anu.registrationapp.ui.RegisterActivity.USER_INFO;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.anu.registrationapp.R;
import com.anu.registrationapp.adapters.CaptchaAdapter;
import com.anu.registrationapp.database.SqLiteHelper;
import com.anu.registrationapp.databinding.ActivityCaptchaBinding;
import com.anu.registrationapp.models.Captcha;
import com.anu.registrationapp.models.User;

import java.util.ArrayList;
import java.util.List;

public class CaptchaActivity extends AppCompatActivity {

    private ActivityCaptchaBinding binding;
    private User user;
    private Boolean isFirstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCaptchaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = (User) getIntent().getSerializableExtra(USER_INFO);

        setRecyclerView(makeCaptchaList());
        setListeners();
    }

    private void setListeners() {
        binding.ivRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFirstTime) {
                    setRecyclerView(refreshList());
                    isFirstTime = false;
                } else {
                    isFirstTime = true;
                    setRecyclerView(makeCaptchaList());
                }
            }
        });
        binding.cbNotRobot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                binding.verifyBtn.setEnabled(isChecked);
            }
        });
    }

    private void setRecyclerView(List<Captcha> captchas) {
        CaptchaAdapter captchaAdapter = new CaptchaAdapter(captchas, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView imageView = (ImageView) view;
                if (imageView.getVisibility() == View.VISIBLE) {
                    imageView.setVisibility(View.GONE);
                    captchas.get(i).setSelected(false);
                } else {
                    captchas.get(i).setSelected(true);
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        });
        binding.rvCaptcha.setLayoutManager(new GridLayoutManager(this, 3));
        binding.rvCaptcha.setAdapter(captchaAdapter);
        binding.verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int count = 0;
                for (int i = 0; i < captchas.size(); i++) {
                    if (captchas.get(i).isSelected() && captchas.get(i).isTrafficLight()) {
                        count++;
                    }
                }
                SqLiteHelper sqLiteHelper = new SqLiteHelper(CaptchaActivity.this);
                if (count == 4) {
                    new AlertDialog.Builder(CaptchaActivity.this)
                            .setTitle("Verified")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    sqLiteHelper.addUser(user);
                                    Intent intent = new Intent(CaptchaActivity.this, AllUsersActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                }
                            })
                            .show();
                } else {
                    new AlertDialog.Builder(CaptchaActivity.this)
                            .setTitle("Not verified")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();
                }

            }
        });


    }

    private List<Captcha> makeCaptchaList() {
        List<Captcha> captchaList = new ArrayList<>();
        captchaList.add(new Captcha(R.drawable.road_1, false, false));
        captchaList.add(new Captcha(R.drawable.road_2, false, false));
        captchaList.add(new Captcha(R.drawable.traffic_light_1, true, false));
        captchaList.add(new Captcha(R.drawable.traffic_lights_7, true, false));
        captchaList.add(new Captcha(R.drawable.road_3, false, false));
        captchaList.add(new Captcha(R.drawable.traffic_lights_5, true, false));
        captchaList.add(new Captcha(R.drawable.traffic_lights_6, true, false));
        captchaList.add(new Captcha(R.drawable.road_4, false, false));
        captchaList.add(new Captcha(R.drawable.road_5, false, false));
        return captchaList;
    }

    private List<Captcha> refreshList() {
        List<Captcha> captchaList = new ArrayList<>();
        captchaList.add(new Captcha(R.drawable.traffic_lights_7, true, false));
        captchaList.add(new Captcha(R.drawable.road_2, false, false));
        captchaList.add(new Captcha(R.drawable.traffic_light_1, true, false));
        captchaList.add(new Captcha(R.drawable.road_1, false, false));
        captchaList.add(new Captcha(R.drawable.road_3, false, false));
        captchaList.add(new Captcha(R.drawable.traffic_lights_6, true, false));
        captchaList.add(new Captcha(R.drawable.road_4, false, false));
        captchaList.add(new Captcha(R.drawable.traffic_lights_5, true, false));
        captchaList.add(new Captcha(R.drawable.road_5, false, false));
        return captchaList;
    }
}