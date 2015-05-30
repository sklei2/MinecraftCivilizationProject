package com.mcp.minecraftcivilizationproject;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath.Axis;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class PeasantVillager extends EntityVillager{

	World w;
	
	public PeasantVillager(World worldIn) {
		super(worldIn);
		w = worldIn;
		System.out.println("I think therefore I am!");		
	}

	public void cutDownATree() {
		this.setVelocity(2, 0, 2);
		this.setLookingForHome();
		
	}
}
