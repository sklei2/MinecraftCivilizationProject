package com.minecraftcivproject.mcp.common.entity;

import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;

public class ItemSearchResult {

    private final Item item;
    private final BlockPos blockPos;

    public ItemSearchResult(Item item, BlockPos blockPos){
        this.item = item;
        this.blockPos = blockPos;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public Item getItem() {
        return item;
    }
}
