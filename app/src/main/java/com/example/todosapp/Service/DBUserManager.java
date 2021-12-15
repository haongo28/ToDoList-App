package com.example.todosapp.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DBUser dbUser;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context context){
        this.context = context;
    }

    public DBManager open() throws SQLException {
        dbUser = new DBUser(context);
        database = dbUser.getWritableDatabase();
        for (int i = 1; i<=3; i++){
            delete(i);
        }

        return this;
    }

    public void close() {
        dbUser.close();
    }

    public void insertUser(String id, String username, String email, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = database.insert("Users", null, contentValues);

        System.out.println("Insert data " + result);
    }

    public Cursor getUser(){
        Cursor cursor = database.rawQuery("Select * from Users", null);
        return cursor;
    }

    public void delete(int id){
        database.delete("Users", "id = " + id, null);
    }
}
