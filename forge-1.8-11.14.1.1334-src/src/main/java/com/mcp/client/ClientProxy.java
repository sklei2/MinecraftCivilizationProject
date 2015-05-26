package com.mcp.client;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import com.mcp.client.render.items.ItemRenderRegister;
import com.mcp.minecraftcivilizationproject.CommonProxy;

public class ClientProxy extends CommonProxy{

	@Override
	public void registerRenderers(){
		// THIS is where all of the actual renderering for icons and items.
	}
	
	@Override
	public void init(FMLInitializationEvent e){
		super.init(e);
		
		ItemRenderRegister.registerItemRenderer();
	}
}
