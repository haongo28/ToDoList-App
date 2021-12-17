package com.example.todosapp.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBToDoManager {
    private DBToDo dbToDo;
    private Context context;
    private SQLiteDatabase database;

    public DBToDoManager(Context context){
        this.context = context;
    }

    public DBToDoManager open() throws SQLException {
        dbToDo = new DBToDo(context);
        database = dbToDo.getWritableDatabase();
        // TODO: nao nhap data thuc nho xoa vong for nay di nho
        for (int i = 1; i<=4; i++){
            delete(i);
        }
        return this;
    }

    public void close() {
        dbToDo.close();
    }

    public void insertToDo(String user_id, String task_name, String complete, String day){
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", user_id);
        contentValues.put("task_name", task_name);
        contentValues.put("complete", complete);
        contentValues.put("day", day);
        long result = database.insert("ToDos", null, contentValues);

        System.out.println("Insert data " + result);
    }

    public Cursor getToDo(){
        Cursor cursor = database.rawQuery("Select * from ToDos", null);
        return cursor;
    }

    public void delete(int id){
        database.delete("ToDos", "user_id = " + id, null);
    }
}
