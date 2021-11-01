package com.example.todosapp.Service;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.todosapp.R;

public class Tools {

    public static void HideSoftKeyBoard(FragmentActivity context) {
        if(context == null) return;

        InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) {
            View view = context.getCurrentFocus();
            if(view != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static void CreateAndShowForeground(View view, int alpha){
        Drawable drawableDim = view.getForeground();
        if(drawableDim == null){
            drawableDim = ResourcesCompat.getDrawable(view.getResources(), R.drawable.foreground_dim, null);
            view.setForeground(drawableDim);
        }

        if (drawableDim != null) {
            drawableDim.setAlpha(alpha);
        }
    }
}