package com.mcp.minecraftcivilizationproject;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDoor;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
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
		buildFrame();
		fillWalls();
		buildRoof();
		furnishHouse();
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
		int[][] layout = { {1, 1, 1, 1, 1}, 
						   {1, 1, 1, 1, 1},
						   {1, 1, 1, 1, 1},
						   {1, 1, 1, 1, 1},
						   {1, 1, 1, 1, 1}
						   };
		BuildingUtils.arrayToBuilding(w, layout, location, BuildingUtils.OAK_WOOD);
	}
	
	private void buildFrame(){
		int[][] layout = { {3, 0, 0, 0, 3}, 
						   {0, 0, 0, 0, 0},
						   {0, 0, 0, 0, 0},
						   {0, 0, 0, 0, 0},
						   {3, 0, 0, 0, 3}
						   };
		BuildingUtils.arrayToBuilding(w, layout, location.add(0, 1, 0), BuildingUtils.OAK_WOOD);

		int[][] layout2 = { {1, 1, 1, 1, 1}, 
						    {1, 0, 0, 0, 1},
						    {1, 0, 0, 0, 1},
						    {1, 0, 0, 0, 1},
						    {1, 1, 1, 1, 1}
						    };
		BuildingUtils.arrayToBuilding(w, layout2, location.add(0, 4, 0), BuildingUtils.OAK_WOOD);

	}
	
	private void fillWalls(){
		int[][] layout = { {0, 3, 3, 3, 0}, 
						   {3, 0, 0, 0, 3},
						   {3, 0, 0, 0, 3},
						   {3, 0, 0, 0, 3},
						   {0, 3, 0, 3, 0}
						   };
		BuildingUtils.arrayToBuilding(w, layout, location.add(0, 1, 0), BuildingUtils.OAK_PLANKS);
		ItemDoor.placeDoor(w, location.add(4, 1, 2), EnumFacing.WEST, Block.getBlockById(64));
		
		int[][] layout2 = {{0, 0, 0, 0, 0}, 
						   {0, 0, 0, 0, 0},
						   {0, 0, 0, 0, 0},
						   {0, 0, 0, 0, 0},
						   {0, 0, 1, 0, 0}
						   };
		BuildingUtils.arrayToBuilding(w, layout2, location.add(0, 3, 0), BuildingUtils.OAK_PLANKS);
	}
	
	private void buildRoof(){
		int[][] layout = { {0, 0, 0, 0, 0}, 
						   {0, 1, 1, 1, 0},
						   {0, 1, 1, 1, 0},
						   {0, 1, 1, 1, 0},
						   {0, 0, 0, 0, 0}
						   };
		BuildingUtils.arrayToBuilding(w, layout, location.add(0, 5, 0), BuildingUtils.OAK_PLANKS);

	}
	
	private void furnishHouse(){
		w.setBlockState(location.add(2, 3, 1), Blocks.torch.getStateFromMeta(3));
		w.setBlockState(location.add(2, 3, 3), Blocks.torch.getStateFromMeta(4));
	}
}
