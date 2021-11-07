package com.example.todosapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todosapp.R;

import java.util.List;

public class ListToDoAdapter extends RecyclerView.Adapter<ListToDoAdapter.ListToDoViewHolder>{

    private List<String> mToDoList;

    public void setData (List<String> list){
        this.mToDoList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_options_todo, parent, false);
        return new ListToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListToDoViewHolder holder, int position) {
        String todo = mToDoList.get(position);
        System.out.println(mToDoList);
        System.out.println("Todo: " + todo);
        if (todo == null){
            return;
        }
        holder.tvToDo.setText(todo.trim());

        holder.ivBlackStart.setOnClickListener(view -> {
            holder.ivBlackStart.setVisibility(View.GONE);
            holder.ivBlueStart.setVisibility(View.VISIBLE);
        });

        holder.ivBlueStart.setOnClickListener(view -> {
            holder.ivBlackStart.setVisibility(View.VISIBLE);
            holder.ivBlueStart.setVisibility(View.GONE);
        });
    }

    @Override
    public int getItemCount() {
        if (mToDoList != null){
            return mToDoList.size();
        }
        return 0;
    }

    public class ListToDoViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvToDo;
        private final ImageView ivBlackStart;
        private final ImageView ivBlueStart;

        public ListToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvToDo = itemView.findViewById(R.id.tvOption);
            ivBlackStart = itemView.findViewById(R.id.ivBlackStart);
            ivBlueStart = itemView.findViewById(R.id.ivBlueStart);
        }
    }
}
