package com.minecraftcivproject.mcp.common.entity.task.core;

import com.minecraftcivproject.mcp.common.entity.task.core.manager.TaskManager;
import java.util.List;

public abstract class Task {

    private TaskManager subtaskManager = new TaskManager();

    private boolean started = false;
    private boolean isDone = false;


    public void start(){
        started = true;
        onStart();
    }

    public void update()
    {
        subtaskManager.onTick();
        onTick();
    }

    protected void onStart(){

    }

    protected void onTick()
    {
        subtaskManager.onTick();
    }


    public Task addSubtask(Task task){
        this.subtaskManager.addTask(task);
        return this;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        System.out.println("~~~~~~~~~~~~MARKING TASK DONE~~~~~~~~~~~~");
        this.isDone = true;
    }

    public boolean hasStarted() {
        return started;
    }

    public boolean wasSuccessful(){
        return isDone();
    }


    public void removeTask(Task task){
        this.subtaskManager.removeTask(task);
    }

    public List<Task> getTasks() {
        return subtaskManager.getTasks();
    }

    public boolean isRunningSubtasks(){
        return subtaskManager.getTasks().size() > 0;
    }
}
