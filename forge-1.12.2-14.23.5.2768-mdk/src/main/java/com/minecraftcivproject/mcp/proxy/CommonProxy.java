package com.minecraftcivproject.mcp.proxy;

import com.minecraftcivproject.mcp.World.WorldGenOres;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        GameRegistry.registerWorldGenerator(new WorldGenOres(), 3);
    }
    public void init(FMLInitializationEvent e) {}
    public void postInit(FMLPostInitializationEvent e) {}

    public void registerItemRenderer(Item item, int meta, String id) {
    }
}