package com.minecraftcivproject.mcp.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpawningUtils {
    public static void spawn(World w, Entity e, BlockPos pos){
        e.setPosition(pos.getX(), pos.getY(), pos.getZ());
        w.spawnEntity(e);
    }
}
