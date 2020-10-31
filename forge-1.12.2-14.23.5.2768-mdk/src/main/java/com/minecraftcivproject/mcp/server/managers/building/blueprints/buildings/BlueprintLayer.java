package com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings;


import com.minecraftcivproject.mcp.utils.BlockLookup;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;
import java.util.logging.Logger;

public class BlueprintLayer {

    private Map<String, Block> blockAssignmentMap = new HashMap<>();
    private List<List<Block>> blockLayer = new ArrayList<>();

    private static final Logger LOG = Logger.getLogger("TownBlueprintLayer");

    public BlueprintLayer(Collection<BlockAssignment> blockAssignments, Collection<String> rawLayer){
        createBlockAssignmentMap(blockAssignments);
        createBlockLayer(rawLayer);
    }


    /**
     * Public methods
     */

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

    public int getRows(){
        return blockLayer.size();
    }

    public int getCols(){
        return blockLayer.get(0).size();
    }

    public Map<String, Block> getBlockAssignmentMap() {
        return this.blockAssignmentMap;
    }

    public List<List<Block>> getBlockLayer() {
        return this.blockLayer;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final BlueprintLayer other = (BlueprintLayer) obj;

        return this.isBlockAssignmentMapEqual(other.getBlockAssignmentMap()) && this.isBlockLayerEqual(other.getBlockLayer());
    }


    /**
     * Private methods
     */

    /* @method isBlockAssignmentMapEqual
    // Map is equivalent to a dictionary (lookup table for key-value pairs)
    dict = {
        Dirt : Block.Dirt
        Cobblestone : Block.Cobblestone
        Sand : Block.Sand
    }
    otherDict = {
       Dirt : Block.Dirt
       Obsidian : Block.Obsidian
       Sand : Block.Sand
    }

    Map.Entry<String, Block> dictEntry = dict.entrySet()

    String entryKey = dictEntry.key
    Block otherValue = otherDict.get(entryKey)

    entryValue = dictEntry.value
     */
    private boolean isBlockAssignmentMapEqual(Map<String, Block> other){
        for (Map.Entry<String, Block> entry : this.blockAssignmentMap.entrySet()){

            String entryKey = entry.getKey();

            try {
                Block otherValue = other.get(entryKey);
                if (!entry.getValue().getLocalizedName().equals(otherValue.getLocalizedName())) {
                    return false;
                }
            }
            catch (Exception e) {
                return false;
            }
        }

        return true;
    }

    private boolean isBlockLayerEqual(List<List<Block>> other){
        int i = 0;
        for (List<Block> rowOfBlocks : this.blockLayer) {
            int j = 0;
            for (Block block : rowOfBlocks) {
                Block otherBlock = other.get(i).get(j);
                if (!block.getLocalizedName().equals(otherBlock.getLocalizedName())) {
                    return false;
                }
                ++j;
            }
            ++i;
        }

        return true;
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
