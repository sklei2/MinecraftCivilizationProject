package com.minecraftcivproject.mcp.common.entity.search;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.Collections;

import static com.minecraftcivproject.mcp.utils.PositionUtils.distanceBetween;

public class ItemSearcher {

    private final World world;

    public ItemSearcher(World world){
        this.world = world;
    }

    public ItemSearchResult search(BlockPos startingLocation, int searchSize, Item item){
        return search(startingLocation, searchSize, Collections.singleton(item));
    }

    public ItemSearchResult search(BlockPos startingLocation, int searchSize, Collection<Item> items) {
        BlockPos nearest = null;
        double nearestDistance = Double.MAX_VALUE;
        Item nearestItem = null;

        for(Item item : items){
            ItemSearchResult itemSearchResult = find(startingLocation, searchSize, item);
            BlockPos nearestForThisItem = itemSearchResult.getBlockPos();

            // no result found
            if(nearestForThisItem == null){
                continue;
            }

            // keep track of the nearest item of interest and the distance
            if (nearest == null || distanceBetween(startingLocation, nearestForThisItem) < nearestDistance){
                nearest = nearestForThisItem;
                nearestDistance = distanceBetween(startingLocation, nearestForThisItem);
                nearestItem = itemSearchResult.getItem();
            }
        }

        return new ItemSearchResult(nearestItem, nearest);
    }

    private ItemSearchResult find(BlockPos startingLocation, int searchSize, Item item){
        Block blockToSearchFor = Block.getBlockFromItem(item);

        BlockPos blockPos = new BlockSearcher(world).search(startingLocation, searchSize, blockToSearchFor);
        return new ItemSearchResult(item, blockPos);
    }


}
