package com.mcp.minecraftcivilizationproject;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.village.Village;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * A town that keeps track of villagers and can grow
 * @author rj
 *
 */
public class Town extends Village{
	World w;
	BlockPos center;
	KingVillager king;
	private ArrayList<PeasantVillager> peasants;
	int dirt = 0;
	int cobbleStone = 0;
	int wood = 0;
	int planks = 0;
	
	/**
	 * Create a new town
	 * @param w -  the world 
	 * @param center - the center of the town
	 */
	
	public Town(){
		System.out.println("Town created");
		peasants = new ArrayList<PeasantVillager>();
	}
	
	/**
	 * Puts the town in its own thread and it starts running itself
	 */
	@SideOnly(Side.SERVER)
	private void freeTown(){
		Runnable r = new Runnable(){

			@Override
			public void run() {
				System.out.println("running");
				collectWood();
				System.out.println("Collect wood");
			}
			
		};
		
		Thread t = new Thread(r);
		t.start();
	}
	
	/**
	 * Adds a peasant to the town
	 * @param peasant
	 */
	public void addPeasant(PeasantVillager peasant){
		peasants.add(peasant);
		System.out.println("Peasant added");
	}
	
	private void collectWood(){
		PeasantVillager lumberjack = peasants.get(0);
		lumberjack.cutDownATree();
	}
	
	/**
	 * Returns all of the peasants in this town
	 * @return - peasants in the town
	 */
	public List<PeasantVillager> getPeasants(){
		return peasants;
	}
	
	/**
	 * Adds a house to the town
	 */
	public void addHouse(){
		TownHouse th = new TownHouse(w, center);
	}
	
	public void placeTownInWorld(World w, BlockPos center){
		this.center = center.add(0, -1, 0);
		this.w = w;
	}

	@SideOnly(Side.SERVER)
	public void spawnInitialVillagers() {
		BlockPos spawn = w.getSpawnPoint();
		System.out.println("spawn " + spawn);
		king = new KingVillager(w);
		king.setPosition(spawn.getX(), spawn.getY(), spawn.getZ());
		w.spawnEntityInWorld(king);
		PeasantVillager peasant = new PeasantVillager(w);
		peasants.add(peasant);
		peasant.setPosition(spawn.getX(), spawn.getY(), spawn.getZ());
		w.spawnEntityInWorld(peasant);
		
		this.freeTown();
	}
}
