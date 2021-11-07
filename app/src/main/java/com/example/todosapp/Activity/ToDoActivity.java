package com.example.todosapp.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todosapp.Adapter.ListCompletedAdapter;
import com.example.todosapp.Adapter.ListToDoAdapter;
import com.example.todosapp.R;

import java.util.ArrayList;
import java.util.List;

public class ToDoActivity extends AppCompatActivity {

    private RecyclerView rcvToDo;
    private RecyclerView rcvCompleted;
    private ListToDoAdapter toDoAdapter;
    private ListCompletedAdapter completedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        rcvToDo = findViewById(R.id.rcvToDo);
        rcvCompleted = findViewById(R.id.rcvComplete);

        toDoAdapter = new ListToDoAdapter();
        completedAdapter = new ListCompletedAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvToDo.setLayoutManager(layoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvCompleted.setLayoutManager(linearLayoutManager);

        toDoAdapter.setData(setToDo());
        rcvToDo.setAdapter(toDoAdapter);

        completedAdapter.setData(setCompleted());
        rcvCompleted.setAdapter(completedAdapter);

    }

    private List<String> setToDo() {
        List<String> listToDo = new ArrayList<>();
        listToDo.add("Task 1");
        listToDo.add("Task 2");

        return listToDo;
    }

    private List<String> setCompleted() {
        List<String> listComplete = new ArrayList<>();
        listComplete.add("Task 3");

        return listComplete;
    }
}
