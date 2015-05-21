package com.mcp.minecraftcivilizationproject;

import java.util.ArrayList;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class KingVillager extends EntityVillager{
	ArrayList<PeasantVillager> loyalSubjects;
	Town rulesOver;
	World worldIn;
	
	public KingVillager(World worldIn) {
		super(worldIn);
		this.worldIn = worldIn;
		System.out.println("I think therefore I am King!");
		loyalSubjects = new ArrayList<PeasantVillager>();
	}
	
	public void addLoyalSubject(PeasantVillager p){
		loyalSubjects.add(p);
	}
	
	@Override
	public void setDead(){
		for(PeasantVillager p : loyalSubjects){
			p.setFire(100);
		}
		super.setDead();
	}
	
	public void foundTown(){
		rulesOver = new Town(worldIn, new BlockPos(this));
	}
}
