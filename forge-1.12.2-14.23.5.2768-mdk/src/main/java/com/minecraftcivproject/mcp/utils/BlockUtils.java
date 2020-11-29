package com.minecraftcivproject.mcp.utils;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUtils {

    public static void placeBlock(World world, BlockPos blockPos, Block block){
        world.setBlockState(blockPos, block.getDefaultState());
    }

}
