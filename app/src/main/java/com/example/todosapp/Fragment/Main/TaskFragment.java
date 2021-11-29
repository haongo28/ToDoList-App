package com.example.todosapp.Fragment.Main;


import static com.example.todosapp.Adapter.TaskAdapter.*;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todosapp.Adapter.TaskAdapter;
import com.example.todosapp.Adapter.TaskCalendarAdapter;
import com.example.todosapp.Adapter.TaskCalendarCompletedAdapter;
import com.example.todosapp.Models.Task;
import com.example.todosapp.R;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;


public class TaskFragment extends Fragment {

    RecyclerView rvTask;
    RecyclerView rvTaskCompleted;
    View view;
    LinearLayout layoutTaskComplete, noTaskLayout,TaskLayout;
    TextView tvCountTaskCompleted;
    ImageView imgDown;

    List<Task> mListTask;
    List<Task> mListTaskCompleted = new ArrayList<>();

    TaskAdapter taskAdapter;
    TaskAdapter taskAdapterCompleted;

    Toolbar tbmenu;

    Boolean isExpand =true;

    Chip cAll;
    Chip cPersonal;
    Chip cHomework;
    String isFilter = "all";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_task, container, false);
        InitComponent();
        setDataListTask();
        SetupToolBar();
        setOnClick();
        return view;
    }

    private void InitComponent() {
        rvTask = view.findViewById(R.id.rv_task);
        rvTaskCompleted = view.findViewById(R.id.rv_task_completed);
        layoutTaskComplete = view.findViewById(R.id.layout_task_completed);
        tvCountTaskCompleted = view.findViewById(R.id.tv_count_task_completed);
        imgDown = view.findViewById(R.id.img_down);
        tbmenu = view.findViewById(R.id.tb_task);
        noTaskLayout = view.findViewById(R.id.no_task_layout);
        TaskLayout = view.findViewById(R.id.task_layout);
        cAll = view.findViewById(R.id.cAll);
        cPersonal = view.findViewById(R.id.cPersonal);
        cHomework = view.findViewById(R.id.cHomework);
    }

    private void setDataListTask(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvTask.setLayoutManager(linearLayoutManager);
        rvTask.setFocusable(false);
        rvTask.setNestedScrollingEnabled(false);

        taskAdapter = new TaskAdapter();
        mListTask = setListTask();

        taskAdapter.setData(mListTask, (task, position) -> {
                mListTask.remove(task);
                taskAdapter.notifyItemRemoved(position);
                taskAdapter.notifyItemRangeRemoved(position, mListTask.size());

                task.setCompleted(true);
                mListTaskCompleted.add(task);
                taskAdapterCompleted.notifyDataSetChanged();

                showorhiddenLayoutCompleted();
        });
        rvTask.setAdapter(taskAdapter);
    }

    private void setOnClick (){
        cAll.setOnClickListener(this::onClick);
        cPersonal.setOnClickListener(this::onClick);
        cHomework.setOnClickListener(this::onClick);

        onClick(view);
    }

    public void onClick(View view) {
        if (cAll.equals(view)) {
            isFilter = "all";
        } else if (cPersonal.equals(view)) {
            isFilter = "personal";
        } else if (cHomework.equals(view)) {
            isFilter = "homework";
        }
        setDataListTaskCompleted();
        setDataListTask();
    }

    private List<Task> setListTask (){
        List<Task> defaultListTask = getListTask();
        List<Task> filteredListTask = new ArrayList<>();
        if (!isFilter.equals("all")){
            for (int i=0; i<defaultListTask.size(); i++){
                Task taskObj = defaultListTask.get(i);
                if (isFilter.equals(taskObj.getCategory())){
                    filteredListTask.add(taskObj);
                }
            }
            return filteredListTask;
        }
        return defaultListTask;
    }

    private void setDataListTaskCompleted(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvTaskCompleted.setLayoutManager(linearLayoutManager);
        rvTaskCompleted.setFocusable(false);
        rvTaskCompleted.setNestedScrollingEnabled(false);

        taskAdapterCompleted = new TaskAdapter();

        taskAdapterCompleted.setData(mListTaskCompleted, (task, position) -> {
            mListTaskCompleted.remove(task);
            taskAdapterCompleted.notifyItemRemoved(position);
            taskAdapterCompleted.notifyItemRangeRemoved(position, mListTaskCompleted.size());

            task.setCompleted(false);
            mListTask.add(task);
            taskAdapter.notifyDataSetChanged();

            showorhiddenLayoutCompleted();

        });
        rvTaskCompleted.setAdapter(taskAdapterCompleted);
    }
    private List<Task> getListTask(){
        List<Task> list = new ArrayList<>();
        list.add(new Task("1", "1", "1", "personal", false, true, new ArrayList<>(), null));
        list.add(new Task("2", "2", "2", "homework", false, false, new ArrayList<>(), null));
        list.add(new Task("3", "3", "3","homework", false, true, new ArrayList<>(), null));
        list.add(new Task("4", "4", "4", "personal",false, false, new ArrayList<>(), null));
        list.add(new Task("5", "5", "4","personal", false, false, new ArrayList<>(), null));
        list.add(new Task("6", "6", "4", "homework",false, false, new ArrayList<>(), null));
        TaskLayout.setVisibility(View.VISIBLE);
        noTaskLayout.setVisibility(View.GONE);
        return list;
    }

    private void showorhiddenLayoutCompleted (){
        if (mListTaskCompleted != null && mListTaskCompleted.size()>0) {
            layoutTaskComplete.setVisibility(view.VISIBLE);
            tvCountTaskCompleted.setText((String.valueOf(mListTaskCompleted.size())));
        }else {
            layoutTaskComplete.setVisibility(view.GONE);
        }

        layoutTaskComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpand){
                    isExpand=false;
                    rvTaskCompleted.setVisibility(view.GONE);
                    imgDown.setImageResource(R.drawable.ic_right);
                }
                else {
                    isExpand=true;
                    rvTaskCompleted.setVisibility(view.VISIBLE);
                    imgDown.setImageResource(R.drawable.ic_arrow_down);
                }
            }
        });
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