package com.example.todosapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.todosapp.R;

public class TaskFragment extends Fragment {
    private View view;

    private TextView tvCalendarStatus;
    private TextView tvTimeStatus;
    private TextView tvRepeatStatus;
    private TextView tvNoteStatus;
    private TextView tvAttachStatus;
    private Spinner spFavorite;
    private Toolbar tbMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_task, container, false);
        InitComponent();
        setText();
        setupSpinner();
        setupToolbar();
        return view;
    }

    private void InitComponent() {
        tvCalendarStatus = view.findViewById(R.id.tv_calendar_status);
        tvTimeStatus = view.findViewById(R.id.tv_time_status);
        tvRepeatStatus = view.findViewById(R.id.tv_repeat_status);
        tvNoteStatus = view.findViewById(R.id.tv_note_status);
        tvAttachStatus = view.findViewById(R.id.tv_attach_status);
        spFavorite = view.findViewById(R.id.spFavorite);
        tbMenu = view.findViewById(R.id.tb_task_toolbar);
    }

    private void setText(){
        tvCalendarStatus.setText(R.string.app_name);
        tvCalendarStatus.setBackgroundResource(R.drawable.task_item_border_radius); // neu muon co cai border nhu hinh
        tvTimeStatus.setText(R.string.add);
        tvRepeatStatus.setText(R.string.add);
        tvNoteStatus.setText(R.string.add);
        tvAttachStatus.setText(R.string.add);
    }

    private void setupSpinner() {
        ArrayAdapter<String> favoriteAdapter = new ArrayAdapter<>(getContext(),
                R.layout.item_favorite_list, getResources().getStringArray(R.array.favorite_list));
        favoriteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFavorite.setAdapter(favoriteAdapter);
    }

    private void setupToolbar(){
        tbMenu.inflateMenu(R.menu.task_menu);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.task_menu, menu);
    }
}