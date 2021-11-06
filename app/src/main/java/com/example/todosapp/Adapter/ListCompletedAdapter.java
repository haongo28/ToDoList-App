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

public class ListCompletedAdapter extends RecyclerView.Adapter<ListCompletedAdapter.ListCompletedViewHolder>{
    private List<String> mCompletedList;

    public void setData (List<String> list){
        this.mCompletedList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListCompletedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_options_finished, parent, false);
        return new ListCompletedViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListCompletedViewHolder holder, int position) {
        String todo = mCompletedList.get(position);
        if (todo == null){
            return;
        }
        holder.tvCompleted.setText(todo);

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
        if (mCompletedList != null){
            return mCompletedList.size();
        }
        return 0;
    }

    public class ListCompletedViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvCompleted;
        private final ImageView ivBlackStart;
        private final ImageView ivBlueStart;

        public ListCompletedViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCompleted = itemView.findViewById(R.id.tvOption);
            ivBlackStart = itemView.findViewById(R.id.ivBlackStart);
            ivBlueStart = itemView.findViewById(R.id.ivBlueStart);
        }
    }
}
