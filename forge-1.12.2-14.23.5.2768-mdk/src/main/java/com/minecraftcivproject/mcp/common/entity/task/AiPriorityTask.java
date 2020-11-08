package com.minecraftcivproject.mcp.common.entity.task;

import net.minecraft.entity.ai.EntityAIBase;

public abstract class AiPriorityTask extends EntityAIBase implements Comparable<AiPriorityTask>{

    private int priority;

    public AiPriorityTask(int priority){
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public abstract boolean isDone();
    public abstract boolean hasStarted();

    @Override
    public int compareTo(AiPriorityTask aiPriorityTask) {
        return getPriority() - aiPriorityTask.getPriority();
    }
}
