package com.mcp.minecraftcivilizationproject;


public interface BuildingLayout {
	

	public void executeLayout();
	
	public boolean canExecuteNextStep();
	
	public ResourceCost totalCost();
	
	public ResourceCost nextStepCost();
}
