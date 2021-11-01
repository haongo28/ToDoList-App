package com.example.todosapp.Models;

import java.util.ArrayList;
import java.util.Date;

public class Task {
    private String id;
    private String title;
    private String description;
    private boolean isCompleted;
    private boolean isStart;
    private ArrayList<SubTask> subTasks;
    private Date date;

    public Task() {
        subTasks = new ArrayList<>();
        date = new Date();
    }

    public Task(String id, String title, String description, boolean isCompleted, boolean isStart, ArrayList<SubTask> subTasks, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.isStart = isStart;
        this.subTasks = subTasks;
        this.date = date;
    }

    public Task(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        isCompleted = false;
        isStart = false;
        subTasks = new ArrayList<>();
        date = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(ArrayList<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
