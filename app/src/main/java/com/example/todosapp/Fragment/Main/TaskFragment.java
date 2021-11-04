package com.example.todosapp.Fragment.Main;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todosapp.Adapter.TaskCalendarAdapter;
import com.example.todosapp.Adapter.TaskCalendarCompletedAdapter;
import com.example.todosapp.Models.Task;
import com.example.todosapp.R;


import java.util.ArrayList;


public class TaskFragment extends Fragment {


    View view;
    LinearLayout layoutTask, layoutNoTask, layoutContainer;

    RecyclerView rvCalendar;
    ArrayList<Task> tasks;
    TaskCalendarAdapter adapter;

    RecyclerView rvTaskCompleted;
    ArrayList<Task> tasksCompleted;
    TaskCalendarCompletedAdapter adapterCompleted;
    Toolbar tbmenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_task, container, false);
        InitComponent();
        GetTask();
        SetUpRvTask();
        SetUpRvTaskCompleted();
        SetupToolBar();
        return view;
    }

    private void InitComponent() {
        //layoutContainer = view.findViewById(R.id.layout_calendar_container);
        layoutTask = view.findViewById(R.id.task_layout_calendar);
        layoutNoTask = view.findViewById(R.id.no_task_layout);
        rvCalendar = view.findViewById(R.id.rv_calendar);
        rvTaskCompleted = view.findViewById(R.id.rv_task_completed);
        tbmenu = view.findViewById(R.id.tb_task);
    }

    private void GetTask() {
        tasks = new ArrayList<>();
        tasks.add(new Task("1", "1", "1", true, true, new ArrayList<>(), null));
        tasks.add(new Task("2", "2", "2", true, false, new ArrayList<>(), null));
        tasks.add(new Task("3", "3", "3", true, true, new ArrayList<>(), null));
        tasks.add(new Task("4", "4", "4", true, false, new ArrayList<>(), null));
        tasks.add(new Task("5", "5", "4", true, false, new ArrayList<>(), null));
        tasks.add(new Task("6", "6", "4", true, false, new ArrayList<>(), null));

        tasksCompleted = new ArrayList<>();
        tasksCompleted.add(new Task("1", "123456789", "1", false, true, new ArrayList<>(), null));
        tasksCompleted.add(new Task("3", "123456789", "3", false, false, new ArrayList<>(), null));

        layoutTask.setVisibility(View.VISIBLE);
        layoutNoTask.setVisibility(View.GONE);
    }

    private void SetUpRvTask() {
        adapter = new TaskCalendarAdapter(getContext(), tasks,
                null,
                null);

        rvCalendar.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvCalendar.setAdapter(adapter);
    }

    private void SetUpRvTaskCompleted() {
        adapterCompleted = new TaskCalendarCompletedAdapter(getContext(), tasksCompleted, null,
                null);

        rvTaskCompleted.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvTaskCompleted.setAdapter(adapterCompleted);
    }

    // MENU
    private void SetupToolBar(){
        tbmenu.inflateMenu(R.menu.menu_task_layout);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)  {
        inflater= getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_task_layout, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()){
            case R.id.categories_management:
                Toast.makeText(getActivity(),
                        "categories management selected",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.search_task:
                Toast.makeText(getActivity(),
                        "search task selected",
                        Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}