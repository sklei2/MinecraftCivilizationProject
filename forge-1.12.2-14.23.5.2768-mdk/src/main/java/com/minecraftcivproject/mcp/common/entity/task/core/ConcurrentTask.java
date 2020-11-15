package com.minecraftcivproject.mcp.common.entity.task.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Runs multiple tasks at the same time (ie. within the same tick)
 */
public class ConcurrentTask extends Task {

    private List<Task> tasks = new ArrayList<>();
    private boolean started = false;

    public ConcurrentTask addTask(Task task){
        this.tasks.add(task);
        return this;
    }

    public void removeTask(Task task){
        this.tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public boolean shouldExecute(){
        return true;
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return true;
    }

    @Override
    public boolean isInterruptible()
    {
        return true;
    }

    @Override
    public void startExecuting()
    {
        started = true;
    }

    @Override
    public void resetTask()
    {
        started = false;
        tasks.forEach(task -> resetTask());
    }

    @Override
    public void updateTask()
    {
        Collection<Task> tasksToRemove = tasks.stream().filter(Task::isDone).collect(Collectors.toList());
        this.tasks.removeAll(tasksToRemove);

        tasks.forEach(this::executeSubTask);
    }

    @Override
    public boolean isDone() {
        for(Task task : tasks){
            if(task.isDone()){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hasStarted() {
        return started;
    }

    private void executeSubTask(Task task){
        if(!task.hasStarted()){
            startSubTask(task);
        } else {
            updateSubTask(task);
        }
    }

    private void startSubTask(Task task){
        if(!task.shouldExecute()){
            return;
        }

        task.startExecuting();
    }

    private void updateSubTask(Task task){
        if(!task.shouldContinueExecuting()){
            return;
        }

        task.updateTask();
    }
}
