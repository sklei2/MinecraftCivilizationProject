package com.minecraftcivproject.mcp.common.entity;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.logging.Logger;

public class SearchArea {
    private static Logger logger = Logger.getLogger("SearchArea");
    private int xLength;
    private int yLength;
    private int zLength;

    public SearchArea(int xLength, int yLength, int zLength){
        this.xLength = xLength;         // Assigns the field of "xLength" to the object, aka a new SearchArea
        this.yLength = yLength;
        this.zLength = zLength;
    }

    /**
     *  Look at BlockPos.getAllInBox... it is literally designed to do just this
     */
    // MINECRAFT RUNS IN AN EAST SOUTH UP COORDINATE SYSTEM (ESU = +X, +Z, +Y)
    // THE XYZ BLOCKPOC OF A BLOCK IS THE LAYER THE BLOCK IS RESTING ON (refer to Altitude in Minecraft Wiki)
    // This method search for a block starting at the entity's location (inside its legs) in the array [xLength,yLength,zLength]
    public BlockPos searchFor(World world, Block block, BlockPos startingLocation){
        for (int x = startingLocation.getX(); x <= startingLocation.getX() + xLength; x++) {
            for (int y = startingLocation.getY(); y <= startingLocation.getY() + yLength; y++) {
                for (int z = startingLocation.getZ(); z <= startingLocation.getZ() + zLength; z++) {

                    // Used for debugging
                    //logger.info("Searching at " + x + "," + y + "," + z + "...");
                    /* This is for readability in the log
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    */

                    BlockPos pos = new BlockPos(x, y, z);
                    // Used for debugging - prints every block the entity finds
                    //logger.info("Found " + world.getBlockState(pos).getBlock().getLocalizedName() + " at " + x + "," + y + "," + z);

                    if (world.getBlockState(pos).getBlock().equals(block)) {
                        //logger.info(block.getLocalizedName() + " was found at " + x + "," + y + "," + z + "!!!");
                        return pos;
                    }

                }
            }
        }
        return startingLocation;
    }
}
