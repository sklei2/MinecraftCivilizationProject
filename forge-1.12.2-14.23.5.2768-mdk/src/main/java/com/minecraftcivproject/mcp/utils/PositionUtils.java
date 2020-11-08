package com.minecraftcivproject.mcp.utils;

import net.minecraft.util.math.BlockPos;

public class PositionUtils {

    public static double distanceBetween(BlockPos a, BlockPos b){
        return a.getDistance(b.getX(), b.getY(), b.getZ());
    }

}
