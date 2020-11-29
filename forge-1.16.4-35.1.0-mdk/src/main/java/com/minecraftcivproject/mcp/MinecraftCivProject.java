package com.minecraftcivproject.mcp;

import com.minecraftcivproject.mcp.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;


/**
 * You can use @SubscribeEvent and let the Event Bus discover methods to call
 * You can use @Mod.EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD Event Bus for receiving Registry Events)
 */

@Mod(MinecraftCivProject.MODID)
@Mod.EventBusSubscriber(modid = MinecraftCivProject.MODID)
public class MinecraftCivProject {

    public static final String MODID = "minecraftcivproject";
    public static final String NAME = "Minecraft Civilization Project";
    public static final String VERSION = "1.0";

//
//    private static final String CLIENTPROXY = "com.minecraftcivproject.mcp.proxy.ClientProxy";
//    private static final String SERVERPROXY = "com.minecraftcivproject.mcp.proxy.ServerProxy";
//
//    @SidedProxy(clientSide = CLIENTPROXY, serverSide = SERVERPROXY)
//    public static CommonProxy PROXY;
//
//    @Mod.EventHandler
//    public void init(FMLInitializationEvent event) {
//        PROXY.init(event);
//    }
//
//    @Mod.EventHandler
//    public void preInit(FMLPreInitializationEvent event) {
//        PROXY.preInit(event);
//    }
//
//    @Mod.EventHandler
//    public void postInit(FMLPostInitializationEvent event) {
//        PROXY.postInit(event);
//    }
}

