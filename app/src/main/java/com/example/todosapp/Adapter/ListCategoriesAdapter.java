package com.example.todosapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todosapp.R;

import java.util.List;

public class ListCategoriesAdapter extends RecyclerView.Adapter<ListCategoriesAdapter.ListCategoriesViewHolder>{

    private List<String> mCategories;

    public void setData (List<String> list){
        this.mCategories = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories, parent, false);
        return new ListCategoriesViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListCategoriesViewHolder holder, int position) {
        String category = mCategories.get(position);
        if (category == null){
            return;
        }
        holder.tvDescription.setText(category);

        holder.ivMenu.setOnClickListener(this::showPopupMenu);
    }

    private void showPopupMenu (View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.inflate(R.menu.categories_menu);
        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        if (mCategories != null){
            return mCategories.size();
        }
        return 0;
    }

    public class ListCategoriesViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvDescription;
        private final ImageView ivMenu;

        public ListCategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivMenu = itemView.findViewById(R.id.ivMenu);
        }
    }
}
