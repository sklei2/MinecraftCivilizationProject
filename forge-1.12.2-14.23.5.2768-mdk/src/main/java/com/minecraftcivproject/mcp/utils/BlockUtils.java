package com.minecraftcivproject.mcp.utils;

import com.minecraftcivproject.mcp.MinecraftCivProject;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUtils {

    public static void placeBlock(BlockPos blockPos, Block block){
        World w = MinecraftCivProject.getWorld();
        w.setBlockState(blockPos, block.getDefaultState());
    }
}
