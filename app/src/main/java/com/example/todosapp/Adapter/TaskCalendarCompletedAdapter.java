package com.example.todosapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todosapp.Models.Task;
import com.example.todosapp.R;
import com.example.todosapp.Service.ItemClick;
import com.example.todosapp.Service.ItemLongClick;

import java.util.ArrayList;

public class TaskCalendarCompletedAdapter extends RecyclerView.Adapter<TaskCalendarAdapter.ViewHolder> {
    Context context;
    ArrayList<Task> tasks;
    ItemClick OnItemClick;
    ItemLongClick OnItemLongClick;


    public TaskCalendarCompletedAdapter(Context context, ArrayList<Task> tasks, ItemClick onItemClick, ItemLongClick onItemLongClick) {
        this.context = context;
        this.tasks = tasks;
        OnItemClick = onItemClick;
        OnItemLongClick = onItemLongClick;
    }

    @NonNull
    @Override
    public TaskCalendarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null)
            context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_task_calendar_completed, parent, false);
        return new TaskCalendarAdapter.ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull TaskCalendarAdapter.ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.tvTitle.setText(task.getTitle());
        holder.tvTitle.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.itemLayout.setCardBackgroundColor(R.color.primaryColorDisabled);
        if (task.getSubTasks() == null || task.getSubTasks().size() == 0)
            holder.imgBranch.setVisibility(View.GONE);
        else {
            holder.imgBranch.setVisibility(View.VISIBLE);
        }
        if (task.isStart()) {
            holder.btnStart.setImageResource(R.drawable.ic_start_full);
        } else {
            holder.btnStart.setImageResource(R.drawable.ic_star_border);
        }

        holder.itemView.setOnClickListener(v -> OnItemClick.OnItemClick(position));
        holder.itemView.setOnLongClickListener(v -> {
            OnItemLongClick.ItemLongClick(position, holder.itemView);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        if (tasks != null)
            return tasks.size();
        return 0;
    }
}
