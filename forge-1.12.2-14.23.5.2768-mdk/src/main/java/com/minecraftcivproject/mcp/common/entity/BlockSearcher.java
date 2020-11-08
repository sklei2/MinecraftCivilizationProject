package com.minecraftcivproject.mcp.common.entity;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSearcher {

    private final World world;

    public BlockSearcher(World world){
        this.world = world;
    }

    public BlockPos search(BlockPos startingPosition, int searchSize, Block block){
        // TODO: there's probably too much logic in the search area class
        return new SearchArea(searchSize, searchSize, searchSize).searchFor(world, block, startingPosition);
    }
}
