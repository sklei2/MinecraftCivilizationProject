package com.mcp.server;

import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import com.mcp.minecraftcivilizationproject.CommonProxy;

public class ServerProxy extends CommonProxy{
	
	public void serverStarted(FMLServerStartingEvent event){
		System.out.println("The server has been started. I am the server");
	}
}
