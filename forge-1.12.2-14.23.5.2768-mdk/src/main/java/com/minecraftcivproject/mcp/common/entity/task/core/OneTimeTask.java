package com.minecraftcivproject.mcp.common.entity.task.core;

public class OneTimeTask extends Task {

    private boolean isDone = false;
    private boolean hasStarted = false;

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public boolean hasStarted() {
        return hasStarted;
    }

    public void setDone() {
        System.out.println("~~~~~~~~~~~~MARKING TASK DONE~~~~~~~~~~~~");
        this.isDone = true;
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public boolean shouldContinueExecuting(){
        return !isDone();
    }

    @Override
    public void startExecuting(){
        this.hasStarted = true;
    }
}
