package com.example.todosapp.Fragment.Main;

import static android.view.Gravity.RIGHT;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todosapp.Adapter.TaskCalendarAdapter;
import com.example.todosapp.Adapter.TaskCalendarCompletedAdapter;
import com.example.todosapp.Models.Task;
import com.example.todosapp.R;
import com.example.todosapp.Service.Tools;

import java.util.ArrayList;


public class CalendarFragment extends Fragment {


    View view;
    CalendarView calendarView;
    LinearLayout layoutTask, layoutNoTask, layoutContainer;

    RecyclerView rvCalendar;
    ArrayList<Task> tasks;
    TaskCalendarAdapter adapter;

    RecyclerView rvCalendarCompleted;
    ArrayList<Task> tasksCompleted;
    TaskCalendarCompletedAdapter adapterCompleted;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar, container, false);
        InitComponent();
        GetTask();
        SetUpRvTask();
        SetUpRvTaskCompleted();
        return view;
    }

    private void InitComponent() {
        layoutContainer = view.findViewById(R.id.layout_calendar_container);
        layoutTask = view.findViewById(R.id.task_layout_calendar);
        layoutNoTask = view.findViewById(R.id.no_task_layout_calendar);
        calendarView = view.findViewById(R.id.calendar_view);
        rvCalendar = view.findViewById(R.id.rv_calendar);
        rvCalendarCompleted = view.findViewById(R.id.rv_calendar_completed);
    }

    private void GetTask() {
        tasks = new ArrayList<>();
        tasks.add(new Task("1", "1", "1", "personal",true, true, new ArrayList<>(), null));
        tasks.add(new Task("2", "2", "2", "homework",true, false, new ArrayList<>(), null));
        tasks.add(new Task("3", "3", "3", "personal",true, true, new ArrayList<>(), null));
        tasks.add(new Task("4", "4", "4", "personal",true, false, new ArrayList<>(), null));
        tasks.add(new Task("5", "5", "4", "homework",true, false, new ArrayList<>(), null));
        tasks.add(new Task("6", "6", "4", "homework",true, false, new ArrayList<>(), null));

        tasksCompleted = new ArrayList<>();
        tasksCompleted.add(new Task("1", "123456789", "1", "homework",false, true, new ArrayList<>(), null));
        tasksCompleted.add(new Task("3", "123456789", "3", "homework",false, false, new ArrayList<>(), null));

        layoutTask.setVisibility(View.VISIBLE);
        layoutNoTask.setVisibility(View.GONE);
    }

    private void SetUpRvTask() {
        adapter = new TaskCalendarAdapter(getContext(), tasks,
                position -> Toast.makeText(getContext(), "Click", Toast.LENGTH_SHORT).show(),
                (position, view) -> ShowPopupMenu(position, view, false));

        rvCalendar.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvCalendar.setAdapter(adapter);
    }

    private void SetUpRvTaskCompleted() {
        adapterCompleted = new TaskCalendarCompletedAdapter(getContext(), tasksCompleted,
                position -> Toast.makeText(getContext(), "Click", Toast.LENGTH_SHORT).show(),
                (position, view) -> ShowPopupMenu(position, view, true));

        rvCalendarCompleted.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvCalendarCompleted.setAdapter(adapterCompleted);
    }

    @SuppressLint({"RtlHardcoded", "NonConstantResourceId"})
    private void ShowPopupMenu(int position, View anchorView, boolean isCompleted) {
        Tools.CreateAndShowForeground(layoutContainer, 40);
        PopupMenu popupMenu = new PopupMenu(getContext(), anchorView);
        popupMenu.getMenuInflater().inflate(R.menu.menu__tem_task_calendar, popupMenu.getMenu());
        if (isCompleted) {
            popupMenu.getMenu().findItem(R.id.set_completed_task_state).setTitle(getString(R.string.set_un_completed));
        }


        popupMenu.setGravity(RIGHT);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.set_completed_task_state:
                    Toast.makeText(getContext(), "Sửa " + position + isCompleted, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.delete_task:
                    Toast.makeText(getContext(), "Xóa " + position, Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        });
        popupMenu.setOnDismissListener(menu -> Tools.CreateAndShowForeground(layoutContainer, 0));
        popupMenu.show();
    }
}
