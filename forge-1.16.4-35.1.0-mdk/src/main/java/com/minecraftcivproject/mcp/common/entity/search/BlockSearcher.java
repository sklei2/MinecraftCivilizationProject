package com.minecraftcivproject.mcp.common.entity.search;

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
        return SearchArea.searchFor(world, block, startingPosition, searchSize, 3, 3);
    }
}
