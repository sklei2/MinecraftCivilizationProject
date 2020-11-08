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
    public BlockPos pos;
    private boolean boundFound;
    public BlockPos startingLocation;


    public SearchArea(int xLength, int yLength, int zLength) {
        this.xLength = xLength;  // Assigns the field of "xLength" to the object, aka a new SearchArea
        this.yLength = yLength;
        this.zLength = zLength;
    }

    /**
     *  This method searches for a block starting at the entity's location (inside its legs) spiralling outwards (eventually) in the array [xLength,yLength,zLength]
     *  THE XYZ BLOCKPOS OF A BLOCK IS THE LAYER THE BLOCK IS RESTING ON (refer to Altitude in Minecraft Wiki)
     *  MINECRAFT RUNS IN AN EAST SOUTH UP COORDINATE SYSTEM (ESU = +X, +Z, +Y)
     */
    // TODO: Spiral outwards starting from a center point of startingLocation
    // BlockPos.getAllInBox seems to do something similar, but is way more complicated and returns a collection of BlockPos objects (not way to know how big it is)
    public void search(World world, Block block, BlockPos startingLocation){
        this.startingLocation = startingLocation;
        int srtX = startingLocation.getX();
        int srtY = startingLocation.getY();
        int srtZ = startingLocation.getZ();
        int endX = startingLocation.getX() + xLength;
        int endY = startingLocation.getY() + yLength;
        int endZ = startingLocation.getZ() + zLength;
        logger.info("Search area is x: " + srtX + "-" + endX + " y: " + srtY + "-" + endY + " z: " + srtZ + "-" + endZ);

        for (int x = srtX; x <= endX; x++) {
            for (int y = srtY; y <= endY; y++) {
                for (int z = srtZ; z <= endZ; z++) {

                    // Used for debugging
//                    logger.info("Searching at " + x + "," + y + "," + z + "...");
                    //This is for readability in the log
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }


                    BlockPos searchPos = new BlockPos(x, y, z);
                    // Used for debugging - prints every block the entity finds
//                    logger.info("Found " + world.getBlockState(pos).getBlock().getLocalizedName() + " at " + x + "," + y + "," + z);

                    if (world.getBlockState(searchPos).getBlock().equals(block)) {
                        logger.info(block.getLocalizedName() + " was found at " + x + "," + y + "," + z + "!!!");
                        this.pos = searchPos;
                        return;
                    }

                }
            }
        }
        this.pos = startingLocation;
    }

    public void setSearchWindow(int xLength, int yLength, int zLength) {
        this.xLength = xLength;
        this.yLength = yLength;
        this.zLength = zLength;
    }

    public boolean wasBlockFound() {
        return !(this.pos == this.startingLocation);
    }

    public BlockPos getBlockPos() {
        return this.pos;
    }

}
