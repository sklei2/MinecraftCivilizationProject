package com.minecraftcivproject.mcp.server.managers.building.blueprints;


import com.minecraftcivproject.mcp.utils.BlockLookup;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;
import java.util.logging.Logger;

public class BlueprintLayer {

    private Map<String, Block> blockAssignmentMap = new HashMap<>();
    private List<List<Block>> blockLayer = new ArrayList<>();

    private static final Logger LOG = Logger.getLogger("BlueprintLayer");

    public BlueprintLayer(Collection<BlockAssignment> blockAssignments, Collection<String> rawLayer){
        createBlockAssignmentMap(blockAssignments);
        createBlockLayer(rawLayer);
    }

    public void applyLayer(World world, BlockPos topLeft){

        int zOffset = 0;
        for(List<Block> row : blockLayer){
            applyRow(world, topLeft.add(0, 0, zOffset), row);

            zOffset ++;
        }
    }

    public void applyRow(World world, BlockPos rowStart, List<Block> row){

        int xOffset = 0;
        for(Block block : row){
            world.setBlockState(rowStart.add(xOffset, 0, 0), block.getDefaultState());

            xOffset ++;
        }
    }

    private void createBlockLayer(Collection<String> rawLayer){
        blockLayer = new ArrayList<>();

        for(String row : rawLayer){
            blockLayer.add(stringToBlocks(row));
        }
    }

    private List<Block> stringToBlocks(String s){
        List<Block> blocks = new ArrayList<>();

        for(char c : s.toCharArray()){
            blocks.add(getBlock(Character.toString(c)));
        }

        return blocks;
    }

    private Block getBlock(String s){
        return blockAssignmentMap.get(s);
    }

    private void createBlockAssignmentMap(Collection<BlockAssignment> blockAssignments){
        for(BlockAssignment blockAssignment : blockAssignments){
            blockAssignmentMap.put(blockAssignment.getCharacter(), BlockLookup.find(blockAssignment.getName()));
        }
    }
}
