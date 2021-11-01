package com.example.todosapp.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.todosapp.Adapter.ViewPager2StateAdapter;
import com.example.todosapp.Fragment.OnBoarding.OnBoarding1Fragment;
import com.example.todosapp.Fragment.OnBoarding.OnBoarding2Fragment;
import com.example.todosapp.Fragment.OnBoarding.OnBoarding3Fragment;
import com.example.todosapp.R;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class OnBoardingActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager2 viewPager;
    CircleIndicator3 circleIndicator;
    ViewPager2StateAdapter adapter;
    private long backTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        InitComponent();
        InitAdapter();
    }

    private void InitComponent() {
        viewPager = findViewById(R.id.viewpager2_on_boarding);
        circleIndicator = findViewById(R.id.circle_indicator3_on_boarding);
    }

    private void InitAdapter() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new OnBoarding1Fragment());
        fragments.add(new OnBoarding2Fragment());
        fragments.add(new OnBoarding3Fragment());
        adapter = new ViewPager2StateAdapter(this, fragments, null);
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
        circleIndicator.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int currentItem = viewPager.getCurrentItem();
        if(currentItem != viewPager.getItemDecorationCount()- 1){
            viewPager.setCurrentItem(currentItem + 1);
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBackPressed() {
        if (backTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, getString(R.string.back_press), Toast.LENGTH_SHORT).show();
        }

        backTime = System.currentTimeMillis();
    }
}