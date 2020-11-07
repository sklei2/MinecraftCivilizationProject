package com.minecraftcivproject.mcp.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpawningUtils {
    public static void spawn(Entity entity, World world, BlockPos pos){
        entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
        world.spawnEntity(entity);
    }
}
