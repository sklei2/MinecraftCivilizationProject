package com.minecraftcivproject.mcp.common.entity.task.core;

import net.minecraft.entity.ai.EntityAIBase;

/**
 * Wraps the existing garbage minecraft tasks that run continuously
 */
public class ContinuousTask extends Task {

    private final EntityAIBase wrappedTask;
    private boolean hasStarted = false;

    public ContinuousTask(EntityAIBase entityAIBase){
        this.wrappedTask = entityAIBase;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean hasStarted() {
        return hasStarted;
    }

    @Override
    public boolean shouldExecute() {
        return wrappedTask.shouldExecute();
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return wrappedTask.shouldContinueExecuting();
    }
    @Override
    public boolean isInterruptible()
    {
        return wrappedTask.isInterruptible();
    }

    @Override
    public void start()
    {
        this.hasStarted = true;
        wrappedTask.startExecuting();
    }

    @Override
    public void resetTask()
    {
        wrappedTask.resetTask();
    }

    @Override
    public void onTick()
    {
        wrappedTask.updateTask();
    }

    @Override
    public void setMutexBits(int mutexBitsIn)
    {
        wrappedTask.setMutexBits(mutexBitsIn);
    }

    @Override
    public int getMutexBits()
    {
        return wrappedTask.getMutexBits();
    }
}
