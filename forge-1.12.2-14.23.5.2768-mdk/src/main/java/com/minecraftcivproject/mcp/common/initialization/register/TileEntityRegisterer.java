package com.minecraftcivproject.mcp.common.initialization.register;

import com.minecraftcivproject.mcp.MinecraftCivProject;
import com.minecraftcivproject.mcp.server.managers.building.construction.resource.ResourceBinTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRegisterer {

    public static void registerTileEntities(){
        registerTileEntity(ResourceBinTileEntity.class, "chestTest");
    }

    private static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String name){
        GameRegistry.registerTileEntity(tileEntityClass, new ResourceLocation(MinecraftCivProject.MODID, name));
    }
}
