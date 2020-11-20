package com.minecraftcivproject.mcp.common.entity.task.core;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A task that executes sub tasks in sequential order
 */
public class MultiStepTask extends OneTimeTask {

    private final Queue<Task> tasks = new LinkedList<>();
    private Task taskInProgress = null;

    public MultiStepTask addSubtask(Task task){
        tasks.add(task);

        return this;
    }

    @Override
    public void startExecuting(){
        super.startExecuting();
    }

    @Override
    public void updateTask() {

        // handle finishing a task
        if(isThereACompletedTask()){
            onSubtaskCompleted(taskInProgress);
            System.out.println("Sub-task is done: " + taskInProgress);
            taskInProgress = null;
        }

        // if there's no running task
        if(taskInProgress == null){
            if(tasks.size() > 0){
                // set the next task
                taskInProgress = tasks.remove();
                taskInProgress.startExecuting();

                System.out.println("Execute next task: " + taskInProgress.getClass() + ", number of tasks remaining: " + tasks.size());
            } else {
                onNoRemainingSubtasks();
            }
        } else {
            taskInProgress.updateTask();
        }
    }

    @Override
    public boolean isDone() {
        return taskInProgress == null && tasks.isEmpty();
    }

    public Task getTaskInProgress(){
        return taskInProgress;
    }

    protected void onSubtaskCompleted(Task completedTask){

    }

    protected void onNoRemainingSubtasks(){

    }

    private boolean isThereACompletedTask(){
        return taskInProgress != null && taskInProgress.isDone();
    }
}
