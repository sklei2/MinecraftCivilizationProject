package com.mcp.client;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import com.mcp.client.render.items.ItemRenderRegister;
import com.mcp.minecraftcivilizationproject.CommonProxy;
import com.mcp.minecraftcivilizationproject.recipes.ModRecipes;

public class ClientProxy extends CommonProxy{

	@Override
	public void registerRenderers(){
		// THIS is where all of the actual renderering for icons and items.
		ItemRenderRegister.registerItemRenderer();
	}
	
	@Override
	public void init(FMLInitializationEvent e){
		super.init(e);
		System.out.println("should be here");
		ItemRenderRegister.registerItemRenderer();
	}
	
	@Override
	public void registerRecipes(){
		ModRecipes.registerRecipes(); // create and register every recipe
	}
}
