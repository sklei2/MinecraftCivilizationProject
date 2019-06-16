package com.minecraftcivproject.mcp.utils;

import com.minecraftcivproject.mcp.MinecraftCivProject;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpawningUtils {
    public static void spawn(Entity entity, BlockPos pos){
        entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
        World world = MinecraftCivProject.getWorld();
        world.spawnEntity(entity);
    }
}
