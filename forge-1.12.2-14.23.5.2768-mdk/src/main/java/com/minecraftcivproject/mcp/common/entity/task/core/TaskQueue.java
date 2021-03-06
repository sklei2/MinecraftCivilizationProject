package com.minecraftcivproject.mcp.common.entity.task.core;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A task that executes sub tasks in sequential order
 */
public class TaskQueue extends Task {

    private final Queue<Task> tasks = new LinkedList<>();
    private Task subtaskInProgress = null;

    public TaskQueue then(Task task){
        tasks.add(task);

        return this;
    }

    @Override
    public void onTick() {

        // Handle finishing a task
        //System.out.println("Sub-task in progress is: " + subtaskInProgress);
        if (isThereACompletedTask()) {
            onSubtaskCompleted(subtaskInProgress);
            //System.out.println("Sub-task is done: " + subtaskInProgress);
            subtaskInProgress = null;
        }

        // If there's no running task
        if (subtaskInProgress == null) {
            if(tasks.size() > 0){
                // Set the next task
                subtaskInProgress = tasks.remove();
                subtaskInProgress.start();

                //System.out.println("Execute next task: " + subtaskInProgress + ", number of tasks remaining: " + tasks.size());
            } else {
                onNoRemainingSubtasks();
            }
        } else {
            subtaskInProgress.update();
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
