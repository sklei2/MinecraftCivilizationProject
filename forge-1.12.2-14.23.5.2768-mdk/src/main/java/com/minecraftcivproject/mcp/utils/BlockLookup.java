package com.minecraftcivproject.mcp.utils;

import net.minecraft.block.Block;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Minecrafts block lookups don't work for testing.
 */
public class BlockLookup {

    private static Map<String, Block> backupTableForTesting = new HashMap<String, Block>(){{
        put("oak_wood", createMockBlock("oak_wood"));
        put("cobblestone", createMockBlock("cobblestone"));
        put("air", createMockBlock("air"));
    }};


    public static Block find(String name){
        Block block = Block.getBlockFromName(name);

        if(block != null){
            return block;
        }

        return backupTableForTesting.get(name);
    }

    //this pains me
    private static Block createMockBlock(String name){
        Block mock = mock(Block.class);
        when(mock.getLocalizedName()).thenReturn(name);

        return mock;
    }
}
