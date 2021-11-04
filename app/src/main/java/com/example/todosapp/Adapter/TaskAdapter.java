package com.example.todosapp.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todosapp.Models.Task;
import com.example.todosapp.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List <Task> mListTask;
    public IListenerClickCheckBox iListenerClickCheckBox;


    public interface IListenerClickCheckBox {
        void onClickCheckBox (Task task, int position);
    }

    public void setData (List<Task> list, IListenerClickCheckBox ListenerClickCheckBox) {
        this.mListTask = list;
        this.iListenerClickCheckBox = ListenerClickCheckBox;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task1, parent,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = mListTask.get(position);
        if (task == null) {
            return;
        }
        holder.tvTask.setText(task.getTitle());
        holder.chbComplete.setChecked(task.isCompleted());

        holder.chbComplete.setOnClickListener(v -> iListenerClickCheckBox.onClickCheckBox(task, position));
    }

    @Override
    public int getItemCount() {
        if (mListTask != null) {
            return mListTask.size();
        }
        return 0;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        private CheckBox chbComplete;
        private TextView tvTask;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

           chbComplete= itemView.findViewById(R.id.chb_complete);
           tvTask= itemView.findViewById(R.id.tv_task);
        }


    }
}
