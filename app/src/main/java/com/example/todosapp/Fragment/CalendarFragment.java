package com.example.todosapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todosapp.Adapter.TaskCalendarAdapter;
import com.example.todosapp.Models.SubTask;
import com.example.todosapp.Models.Task;
import com.example.todosapp.R;

import java.util.ArrayList;


public class CalendarFragment extends Fragment {


    View view;
    RecyclerView rvCalendar;

    TaskCalendarAdapter adapter;
    ArrayList<Task> tasks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar, container, false);
        InitComponent();
        AddTask();
        SetUpRecyclerView();
        return view;
    }

    private void InitComponent() {
        rvCalendar = view.findViewById(R.id.rv_calendar);
    }

    private void AddTask() {
        tasks = new ArrayList<>();
        tasks.add(new Task("1", "1", "1", true, true, new ArrayList<>(), null));
        tasks.add(new Task("2", "2", "2", true, false, new ArrayList<>(), null));
        tasks.add(new Task("3", "3", "3", false, true, new ArrayList<>(), null));
        tasks.add(new Task("4", "4", "4", false, false, new ArrayList<>(), null));

    }

    private void SetUpRecyclerView() {
        adapter = new TaskCalendarAdapter(getContext(), tasks);
        rvCalendar.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvCalendar.setAdapter(adapter);
    }
}