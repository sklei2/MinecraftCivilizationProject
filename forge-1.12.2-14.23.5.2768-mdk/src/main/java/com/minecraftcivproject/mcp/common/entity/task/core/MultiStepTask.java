package com.minecraftcivproject.mcp.common.entity.task.core;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A task that executes sub tasks in sequential order
 */
public class MultiStepTask extends OneTimeTask {

    private final Queue<Task> tasks = new LinkedList<>();
    private Task subtaskInProgress = null;

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
        System.out.println("Sub-task in progress is: " + subtaskInProgress);
        if (isThereACompletedTask()) {
            onSubtaskCompleted(subtaskInProgress);
            System.out.println("Sub-task is done: " + subtaskInProgress);
            subtaskInProgress = null;
        }

        // if there's no running task
        if (subtaskInProgress == null) {
            if(tasks.size() > 0){
                // set the next task
                subtaskInProgress = tasks.remove();
                subtaskInProgress.startExecuting();

                System.out.println("Execute next task: " + subtaskInProgress + ", number of tasks remaining: " + tasks.size());
            } else {
                onNoRemainingSubtasks();
            }
        } else {
            subtaskInProgress.updateTask();
        }
    }

    @Override
    public boolean isDone() {
        return subtaskInProgress == null && tasks.isEmpty();
    }

    public Task getSubtaskInProgress(){
        return subtaskInProgress;
    }

    protected void onSubtaskCompleted(Task completedTask){

    }

    protected void onNoRemainingSubtasks(){

    }

    private boolean isThereACompletedTask(){
        return subtaskInProgress != null && subtaskInProgress.isDone();
    }
}
