package com.minecraftcivproject.mcp.common.entity.task.core;

import net.minecraft.entity.ai.EntityAIBase;

public abstract class Task extends EntityAIBase {
    public abstract boolean isDone();
    public abstract boolean hasStarted();
    public boolean wasSuccessful(){
        return isDone();
    }
}
