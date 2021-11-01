package com.example.todosapp.Fragment.OnBoarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.todosapp.Activity.LoginActivity;
import com.example.todosapp.R;
import com.google.android.material.button.MaterialButton;

public class OnBoarding2Fragment extends Fragment {

    View view;
    MaterialButton btnLogin, btnContinue;
    View.OnClickListener continueClick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_on_boarding2, container, false);
        InitComponent(view);
        HandleEvent();
        return view;
    }
    private void InitComponent(View view) {
        btnLogin = view.findViewById(R.id.btn_login);
        btnContinue = view.findViewById(R.id.btn_continue_boarding_2);
        continueClick = (View.OnClickListener) getActivity();
    }

    private void HandleEvent() {
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

        btnContinue.setOnClickListener(continueClick);
    }

}