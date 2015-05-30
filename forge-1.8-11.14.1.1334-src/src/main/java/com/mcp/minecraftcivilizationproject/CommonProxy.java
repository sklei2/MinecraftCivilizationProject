package com.mcp.minecraftcivilizationproject;

import com.mcp.minecraftcivilizationproject.CivilizationMod.SturdyStickDropManager;
import com.mcp.minecraftcivilizationproject.items.ModItems;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.SideOnly;


public class CommonProxy {

	public void registerRenderers(){}
	
	
	public void preInit(FMLPreInitializationEvent e){		   	    	     	 
   	 	ModItems.createItems(); // create all the mod items
   	 	System.out.println("create mod items");
		int redColor = (255 << 16);
		int orangeColor = (255 << 16)+ (200 << 8);
		EntityRegistry.registerGlobalEntityID(KingVillager.class, "King Villager", EntityRegistry.findGlobalUniqueEntityId(), redColor, orangeColor);
		EntityRegistry.registerGlobalEntityID(PeasantVillager.class, "Peasant Villager", EntityRegistry.findGlobalUniqueEntityId(), orangeColor, redColor);
	}


	public void init(FMLInitializationEvent e) {
		
	}


	public void registerRecipes() {
		
	}
	
}
