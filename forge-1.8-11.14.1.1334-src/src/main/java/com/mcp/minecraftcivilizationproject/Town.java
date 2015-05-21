package com.mcp.minecraftcivilizationproject;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.village.Village;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class Town extends Village{
	
	public Town(World w, BlockPos center){
		center = center.add(0, -1, 0);
		
	}
}
