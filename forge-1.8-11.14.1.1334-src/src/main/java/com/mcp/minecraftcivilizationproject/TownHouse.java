package com.mcp.minecraftcivilizationproject;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class TownHouse implements BuildingLayout{
	private ResourceCost totalCost;
	private boolean isCompleted = false;
	private BlockPos location;
	private World w;

	public TownHouse(World w, BlockPos location) {
		this.location = location;
		this.w = w;
		executeLayout();
	}
	
	@Override
	public void executeLayout() {
		buildFloor();
		isCompleted = true;
	}

	@Override
	public boolean canExecuteNextStep() {
		return !isCompleted;
	}

	@Override
	public ResourceCost totalCost() {
		return totalCost;
	}

	@Override
	public ResourceCost nextStepCost() {
		return totalCost;
	}
	
	private void buildFloor(){
		//BuildingUtils.makeSquareHorizontal(w, location, 5, BuildingUtils.OAK_WOOD);
		//BuildingUtils.makeSquareVerticalEW(w, location, 5, BuildingUtils.OAK_WOOD);
		//BuildingUtils.makeSquareVerticalNS(w, location, 5, BuildingUtils.OAK_WOOD);
		//BuildingUtils.makeSquareVerticalNS(w, location.add(4, 0, 0), 5, BuildingUtils.OAK_WOOD);
		//BuildingUtils.makeSquareVerticalEW(w, location.add(0, 0, 4), 5, BuildingUtils.OAK_WOOD);
		int[][] layout = { {5, 4, 3, 2, 1} };
		BuildingUtils.arrayToBuilding(w, layout, location, BuildingUtils.OAK_WOOD);
	}

}
