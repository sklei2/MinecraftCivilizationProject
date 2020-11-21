package com.minecraftcivproject.mcp.common.entity.task.core;

import net.minecraft.entity.ai.EntityAIBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Task extends EntityAIBase {

    private List<Task> tasks = new ArrayList<>();

    private boolean started = false;
    private boolean isDone = false;

    @Override
    public boolean shouldExecute(){
        return true;
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return !isDone();
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
        start();
    }

    @Override
    public void resetTask()
    {
        started = false;
        tasks = new ArrayList<>();
    }

    @Override
    public void updateTask()
    {
        int originalNumberOfTasks = tasks.size();

        Collection<Task> tasksToRemove = tasks.stream().filter(Task::isDone).collect(Collectors.toList());
        this.tasks.removeAll(tasksToRemove);

        tasks.forEach(this::executeSubTask);

        int remainingTasks = tasks.size();


        if(originalNumberOfTasks > 0 && remainingTasks == 0){
            onAllSubtasksComplete();
        }

        onTick();
    }

    protected void start(){

    }

    protected void onTick(){

    }

    protected void onAllSubtasksComplete(){

    }

    public Task addSubtask(Task task){
        this.tasks.add(task);
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
        this.tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean isRunningSubtasks(){
        return tasks.size() > 0;
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
