package com.minecraftcivproject.mcp.common.entity.search;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class SearchArea {

    /**
     * Searches up and down in the y direction and in a spiral pattern for the X,Z plane
     */
    public static BlockPos searchFor(World world, Block block, BlockPos startingLocation, int searchSize, int searchHeightUp, int searchHeightDown){
        int furthestYSearch = Math.max(searchHeightUp, searchHeightDown);

        // Search at the starting Y level first
        BlockPos startingYSearchResult = searchPlane(block, world, startingLocation.getX(), startingLocation.getY(), startingLocation.getZ(), searchSize);
        if(startingYSearchResult != null){
            return startingYSearchResult;
        }

        // Search up 1, down 1, up 2, down 2, up 3, down 3, ...
        for(int ySearchDistance = 1; ySearchDistance < furthestYSearch; ++ySearchDistance){

            // should we search up?
            if(ySearchDistance >= searchHeightUp){
                int yLevel = startingLocation.getY() + ySearchDistance;

                BlockPos yLevelSearchResult = searchPlane(block, world, startingLocation.getX(), yLevel, startingLocation.getZ(), searchSize);
                if(yLevelSearchResult != null){
                    return yLevelSearchResult;
                }
            }

            // should we search down?
            if(ySearchDistance >= searchHeightDown){
                int yLevel = startingLocation.getY() - ySearchDistance;

                BlockPos yLevelSearchResult = searchPlane(block, world, startingLocation.getX(), yLevel, startingLocation.getZ(), searchSize);
                if(yLevelSearchResult != null){
                    return yLevelSearchResult;
                }
            }
        }

        // no search result
        return null;
    }

    // Search an X,Z plane at level Y
    private static BlockPos searchPlane(Block block, World world, int startingX, int startingY, int startingZ, int distanceFromStart){

        // search the start location
        BlockPos currentBlockPos = new BlockPos(startingX, startingY, startingZ);
        if(world.getBlockState(currentBlockPos).getBlock().equals(block)){
            return currentBlockPos;
        }

        // search in a ring out from the start location
        for(int currentDistance = 1; currentDistance <= distanceFromStart; ++currentDistance){
            BlockPos ringSearchResult = searchRingOnPlane(block, world, startingX, startingY, startingZ, currentDistance);
            if(ringSearchResult != null){
                return ringSearchResult;
            }
        }

        //no results found
        return null;
    }


    // Searches in a ring on a y plane
    //
    // Example: startingX = 0, startingZ = 0, distanceFromStart = 3
    // (S) start Location
    // (d) distance from start to search
    //  ring starts in this corner ------>   3333333   Increment X until you are at 2d
    //                                       3     3   Increment Z until you are at 2d
    //                                       3     3   Decrement X until you are at 2d
    //                                       3  S  3   Decrement Z until you are at 2d -1 (to not cover the starting point twice
    //                                       3     3
    //                                       3     3
    //                                       3333333
    //
    private static BlockPos searchRingOnPlane(Block block, World world, int startingX, int startingY, int startingZ, int distanceFromStart){
        int topLeftX = startingX - distanceFromStart;
        int topLeftZ = startingZ - distanceFromStart;

        int topRightX = startingX + distanceFromStart;
        int topRightZ = startingZ + distanceFromStart;

        int bottomRightX = startingX + distanceFromStart;
        int bottomRightZ = startingZ - distanceFromStart;

        int bottomLeftX = startingX - distanceFromStart;
        int bottomLeftZ = startingZ + distanceFromStart;

        int topRowZ = startingZ + distanceFromStart;
        int rightX = startingX + distanceFromStart;
        int bottomRowZ = startingZ - distanceFromStart;
        int leftX = startingX - distanceFromStart;

        // search top row
        for(int currentX = topLeftX; currentX <= topRightX; ++currentX){
            BlockPos currentBlockPos = new BlockPos(currentX, startingY, topRowZ);
            if(world.getBlockState(currentBlockPos).getBlock().equals(block)){
                return currentBlockPos;
            }
        }

        // search right side
        for(int currentZ = topRightZ; currentZ >= bottomRightZ; --currentZ){
            BlockPos currentBlockPos = new BlockPos(rightX, startingY, currentZ);
            if(world.getBlockState(currentBlockPos).getBlock().equals(block)){
                return currentBlockPos;
            }
        }

        // search bottom row
        for(int currentX = bottomRightX; currentX >= bottomLeftX; --currentX){
            BlockPos currentBlockPos = new BlockPos(currentX, startingY, bottomRowZ);
            if(world.getBlockState(currentBlockPos).getBlock().equals(block)){
                return currentBlockPos;
            }
        }

        // search left size (except for starting point)
        for(int currentZ = bottomLeftZ; currentZ < topLeftZ; ++currentZ){
            BlockPos currentBlockPos = new BlockPos(leftX, startingY, currentZ);
            if(world.getBlockState(currentBlockPos).getBlock().equals(block)){
                return currentBlockPos;
            }
        }

        // no result
        return null;
    }
}
