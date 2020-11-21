package com.minecraftcivproject.mcp.common.entity.task.core.manager;

import com.minecraftcivproject.mcp.common.entity.task.core.Task;
import net.minecraft.entity.ai.EntityAIBase;

/**
 * Adapter class that is the only "task" that minecraft is calling. This delegates it out from here.
 */
public class TaskAdapter extends EntityAIBase {

    private final LegacyTaskManager legacyTaskManager;
    private final TaskManager taskManager;

    public TaskAdapter(){
        legacyTaskManager = new LegacyTaskManager();
        taskManager = new TaskManager();
    }

    public TaskAdapter addTask(Task task){
        taskManager.addTask(task);
        return this;
    }

    public TaskAdapter addTask(EntityAIBase task){
        legacyTaskManager.addTask(task);
        return this;
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
        return false;
    }

    @Override
    public void startExecuting()
    {
        legacyTaskManager.start();
    }

    @Override
    public void updateTask()
    {
        legacyTaskManager.onTick();
        taskManager.onTick();
    }

}
