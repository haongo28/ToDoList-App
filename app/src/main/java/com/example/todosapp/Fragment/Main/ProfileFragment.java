package com.example.todosapp.Fragment.Main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.todosapp.Dialog.ExampleDialog;
import com.example.todosapp.R;

public class ProfileFragment extends Fragment {
    View view;
    ImageView imageView;
    //dialog
    //private ExampleDialogListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        imageView =  view.findViewById(R.id.setting_button);
        imageView.setOnClickListener(view ->  openDialog());
        return view;
    }
    public void openDialog() {
        ExampleDialog dialog = new ExampleDialog();
        Log.d("check","create dialog");
        dialog.show(getActivity().getSupportFragmentManager(), "example dialog");
        Log.d("check","create dialog");
    }

    //@Override
    // public void applyTexts(String username, String password) {
    //textViewUsername.setText(username);
    //textViewPassword.setText(password);
    // }

}