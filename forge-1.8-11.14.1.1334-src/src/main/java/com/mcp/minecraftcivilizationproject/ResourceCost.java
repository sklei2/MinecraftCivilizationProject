package com.mcp.minecraftcivilizationproject;

public class ResourceCost {
	private int wood;
	private int goldNuggets;
	private int cobbleStone;
	
	public ResourceCost(int wood, int goldNuggets, int cobbleStone){
		this.wood = wood;
		this.goldNuggets = goldNuggets;
		this.cobbleStone = cobbleStone;
	}
	
	public int getWoodCost(){
		return wood;
	}
	
	public int getGoldNuggetCost(){
		return goldNuggets;
	}
	
	public int getCobbleStone(){
		return cobbleStone;
	}
	//comment
}
