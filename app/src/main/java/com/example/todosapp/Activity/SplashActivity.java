package com.example.todosapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todosapp.R;

public class SplashActivity extends AppCompatActivity {

    ImageView logoImg;
    TextView tvName;
    Animation scaleAnimation;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        InitComponents();
        InitAnimation();
        scaleAnimation();
        Navigate();
    }

    private void InitComponents() {
        logoImg = findViewById(R.id.logo_img);
        tvName = findViewById(R.id.tv_name);
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
    }

    private void InitAnimation() {
        scaleAnimation = new ScaleAnimation(0.5f, 1f, 0.5f, 1f, 50f, 50f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setRepeatMode(Animation.INFINITE);
    }

    private void scaleAnimation() {
        logoImg.startAnimation(scaleAnimation);
        tvName.startAnimation(scaleAnimation);
    }

    private void Navigate() {
        Handler handler = new Handler();
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                intent = new Intent(SplashActivity.this, OnBoardingActivity.class);
//                if(isFirstRun){
//                    intent = new Intent(SplashActivity.this, OnBoardingActivity.class);
//                }else{
//                    intent = new Intent(SplashActivity.this, LoginActivity.class);
//                }
                startActivity(intent);
                sharedPreferences.edit().putBoolean("isFirstRun", false).apply();
                finish();
                handler.removeCallbacks(this);
            }
        }, 1000);
    }
}