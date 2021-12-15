package com.example.todosapp.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBUser extends SQLiteOpenHelper {

    public DBUser(@Nullable Context context) {
        super(context, "Todo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Users (id TEXT primary key, username TEXT, email TEXT, password TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Users");
    }

    public Boolean insertUser(String id, String username, String email, String password){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = database.insert("Users", null, contentValues);

        if (result == -1){
            return false;
        }
        return true;
    }

    public Cursor getUser(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from Users", null);
        return cursor;
    }

}
