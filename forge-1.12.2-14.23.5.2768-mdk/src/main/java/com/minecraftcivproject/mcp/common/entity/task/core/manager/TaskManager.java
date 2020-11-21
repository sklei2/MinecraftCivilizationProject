package com.minecraftcivproject.mcp.common.entity.task.core.manager;

import com.minecraftcivproject.mcp.common.entity.task.core.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages dynamic tasks added by the mod
 */
public class TaskManager {

    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task){
        tasks.add(task);
    }

    public void onTick(){
        Collection<Task> tasksToRemove = tasks.stream().filter(Task::isDone).collect(Collectors.toList());
        this.tasks.removeAll(tasksToRemove);

        tasks.forEach(this::executeTask);
    }

    public void removeTask(Task task){
        this.tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    private void executeTask(Task task){
        if(!task.hasStarted()){
            task.start();
        } else {
            task.update();
        }
    }
}
