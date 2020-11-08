package com.minecraftcivproject.mcp.common.entity.task;

public class OneTimeTask extends AiPriorityTask {

    private boolean isDone = false;
    private boolean hasStarted = false;

    public OneTimeTask(int priority) {
        super(priority);
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public boolean hasStarted() {
        return hasStarted;
    }

    public void setDone() {
        System.out.println("MARKING TASK DONE");
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
