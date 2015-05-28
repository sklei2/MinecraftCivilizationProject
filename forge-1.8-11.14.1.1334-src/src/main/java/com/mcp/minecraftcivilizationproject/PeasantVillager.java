package com.mcp.minecraftcivilizationproject;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class PeasantVillager extends EntityVillager{

	public PeasantVillager(World worldIn) {
		super(worldIn);
		System.out.println("I think therefore I am!");		
	}
}
