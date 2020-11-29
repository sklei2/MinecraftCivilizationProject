package com.minecraftcivproject.mcp.common.entity.task.core.manager;

import net.minecraft.entity.ai.EntityAIBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the old minecraft EntityAIBase tasks that most entities have
 */
public class LegacyTaskManager {

    private final List<EntityAIBase> tasks = new ArrayList<>();

    public void addTask(EntityAIBase legacyTask){
        tasks.add(legacyTask);
    }

    public void start(){
        tasks.forEach(this::startTask);
    }

    public void onTick(){
        tasks.forEach(this::updateTask);
    }

    private void startTask(EntityAIBase task){
        if(!task.shouldExecute()){
            return;
        }

        task.startExecuting();
    }

    private void updateTask(EntityAIBase task){
        if(!task.shouldContinueExecuting()){
            return;
        }

        task.updateTask();
    }
}
