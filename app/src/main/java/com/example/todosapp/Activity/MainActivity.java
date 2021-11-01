package com.example.todosapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.todosapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    NavHostFragment navHostFragment;
    NavController navController;
    FloatingActionButton btnAdd;
    CircleImageView imgAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitComponent();
        SetUpBottomNavigation();
        AnimationForAddButton();
    }

    private void InitComponent() {
        bottomNavigation = findViewById(R.id.bottomNavigationView);
        btnAdd = findViewById(R.id.btn_add);
        imgAnim = findViewById(R.id.img_animation);
        
    }

    private void SetUpBottomNavigation() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        navController = Objects.requireNonNull(navHostFragment).getNavController();
        NavigationUI.setupWithNavController(bottomNavigation, navController);
        bottomNavigation.setSelectedItemId(R.id.calendarFragment);

    }

    private void AnimationForAddButton() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.floating_action_button_anim);
        imgAnim.startAnimation(animation);
    }
}