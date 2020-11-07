package com.minecraftcivproject.mcp;

import com.minecraftcivproject.mcp.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


/**
 * You can use @SubscribeEvent and let the Event Bus discover methods to call
 * You can use @Mod.EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD Event Bus for receiving Registry Events)
 */

@Mod(modid = MinecraftCivProject.MODID, name = MinecraftCivProject.NAME, version = MinecraftCivProject.VERSION)
public class MinecraftCivProject {

    public static final String MODID = "minecraftcivproject";
    public static final String NAME = "Minecraft Civilization Project";
    public static final String VERSION = "1.0";


    // GUIs - not sure if this is the best place to put these
    public static final int guiIDLexicon = 1;


    private static final String CLIENTPROXY = "com.minecraftcivproject.mcp.proxy.ClientProxy";
    private static final String SERVERPROXY = "com.minecraftcivproject.mcp.proxy.ServerProxy";

    @SidedProxy(clientSide = CLIENTPROXY, serverSide = SERVERPROXY)
    public static CommonProxy PROXY;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        PROXY.init(event);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PROXY.preInit(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        PROXY.postInit(event);
    }
}

