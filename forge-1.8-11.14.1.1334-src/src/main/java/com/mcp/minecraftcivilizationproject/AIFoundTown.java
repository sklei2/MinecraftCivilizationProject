package com.mcp.minecraftcivilizationproject;

import net.minecraft.entity.ai.EntityAIBase;

public class AIFoundTown extends EntityAIBase{

	@Override
	public boolean shouldExecute() {
		return true;
	}
	
	@Override
    public boolean continueExecuting()
    {
        return this.shouldExecute();
    }

    @Override
    public boolean isInterruptible()
    {
        return true;
    }

    @Override
    public void startExecuting() {
    	
    }

   
    @Override
    public void resetTask() {
    	
    }

    @Override
    public void updateTask() {
    	
    }

}
