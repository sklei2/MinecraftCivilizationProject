package com.mcp.minecraftcivilizationproject;

import com.mcp.minecraftcivilizationproject.items.ModItems;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.SideOnly;


public class CommonProxy {

	public void registerRenderers(){
		// This is going to be implemented in the clientProxy.
	}
	
	
	public void preInit(FMLPreInitializationEvent e){
		ModItems.createItems();
	}


	public void init(FMLInitializationEvent e) {
		
		
	}
	
}
