package com.example.todosapp.Service;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.FragmentActivity;

public class Tools {

    public static void hideSoftKeyBoard(FragmentActivity context) {
        if(context == null) return;

        InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) {
            View view = context.getCurrentFocus();
            if(view != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}