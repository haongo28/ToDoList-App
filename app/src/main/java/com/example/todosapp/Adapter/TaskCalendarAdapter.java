package com.example.todosapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todosapp.Models.Task;
import com.example.todosapp.R;
import com.example.todosapp.Service.ItemClick;
import com.example.todosapp.Service.ItemLongClick;

import java.util.ArrayList;

public class TaskCalendarAdapter extends RecyclerView.Adapter<TaskCalendarAdapter.ViewHolder> {

    Context context;
    ArrayList<Task> tasks;
    ItemClick OnItemClick;
    ItemLongClick OnItemLongClick;


    public TaskCalendarAdapter(Context context, ArrayList<Task> tasks, ItemClick onItemClick, ItemLongClick onItemLongClick) {
        this.context = context;
        this.tasks = tasks;
        OnItemClick = onItemClick;
        OnItemLongClick = onItemLongClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null)
            context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_task_calendar, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);
        if (task == null) {
            Log.e("EEE", "task null");
            return;
        }

        holder.tvTitle.setText(task.getTitle());


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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageButton btnStart;
        ImageView imgBranch;
        CardView itemLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title_task);
            imgBranch = itemView.findViewById(R.id.img_branch);
            btnStart = itemView.findViewById(R.id.btn_start);
            itemLayout = itemView.findViewById(R.id.item_task_calendar_layout);
        }
    }
}
