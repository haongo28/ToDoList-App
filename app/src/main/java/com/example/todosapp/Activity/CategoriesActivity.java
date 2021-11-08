package com.example.todosapp.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todosapp.Adapter.ListCategoriesAdapter;
import com.example.todosapp.R;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    private RecyclerView rcvCategories;
    private ListCategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_categories);

        rcvCategories = findViewById(R.id.rcvCategories);
        categoriesAdapter = new ListCategoriesAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvCategories.setLayoutManager(linearLayoutManager);

        categoriesAdapter.setData(setCategories());
        rcvCategories.setAdapter(categoriesAdapter);
    }

    private List<String> setCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Personal");
        categories.add("Wishlist");

        return categories;
    }

}
