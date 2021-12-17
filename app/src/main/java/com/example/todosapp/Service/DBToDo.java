package com.example.todosapp.Service;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBToDo extends SQLiteOpenHelper {
    public DBToDo(@Nullable Context context) {
        super(context, "Todos.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table ToDos (user_id TEXT, task_name TEXT, complete TEXT, day TEXT) ");
        System.out.println("create ToDo");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
