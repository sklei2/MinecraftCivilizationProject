package com.minecraftcivproject.mcp.common.entity.task;

import net.minecraft.entity.ai.EntityAIBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AiDynamicTasks extends EntityAIBase {

    private List<AiPriorityTask> tasks = new ArrayList<>();

    public AiDynamicTasks(Collection<AiPriorityTask> tasks){
        this.tasks.addAll(tasks);
        Collections.sort(this.tasks);
    }

    public void addTask(AiPriorityTask task){
        this.tasks.add(task);
        Collections.sort(this.tasks);
    }

    public void removeTask(AiPriorityTask task){
        this.tasks.remove(task);
    }

    public List<AiPriorityTask> getTasks() {
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
        // nothing special to do
    }

    @Override
    public void resetTask()
    {
        tasks.forEach(task -> resetTask());
    }

    @Override
    public void updateTask()
    {
        Collection<AiPriorityTask> tasksToRemove = tasks.stream().filter(AiPriorityTask::isDone).collect(Collectors.toList());
        this.tasks.removeAll(tasksToRemove);

        tasks.forEach(this::executeSubTask);
    }

    private void executeSubTask(AiPriorityTask task){
        if(!task.hasStarted()){
            startSubTask(task);
        } else {
            updateSubTask(task);
        }
    }

    private void startSubTask(AiPriorityTask task){
        if(!task.shouldExecute()){
            return;
        }

        task.startExecuting();
    }

    private void updateSubTask(AiPriorityTask task){
        if(!task.shouldContinueExecuting()){
            return;
        }

        task.updateTask();
    }
}
