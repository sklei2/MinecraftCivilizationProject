package com.mcp.minecraftcivilizationproject;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class KingVillager extends EntityVillager{
	Town rulesOver;
	World worldIn;
	
	public KingVillager(World worldIn) {
		super(worldIn);
		this.worldIn = worldIn;
		System.out.println("I think therefore I am King!");
	}
	
	public void addLoyalSubject(PeasantVillager p){
		rulesOver.addPeasant(p);
	}
	
	@Override
	public void setDead(){
		super.setDead();
	}
	
	public void ruleTown(Town town){
		town.king = this;
		this.rulesOver = town;
	}
}
