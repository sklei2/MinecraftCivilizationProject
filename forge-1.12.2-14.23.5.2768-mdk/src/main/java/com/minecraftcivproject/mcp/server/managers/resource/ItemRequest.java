package com.minecraftcivproject.mcp.server.managers.resource;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.ResourceRequirements;
import com.minecraftcivproject.mcp.server.managers.queue.Queueable;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;

import java.util.Collection;

public class ItemRequest implements Queueable {
    // This is the "piece of paper" the construction manager hands with required resource to build the currently queued up project


    private final ItemGroup itemGroup;
    private final BlockPos dropoffLocation;

    public ItemRequest(BlockPos dropoffLocation, ResourceRequirements resourceRequirements){
            this.dropoffLocation = dropoffLocation;

            itemGroup = new ItemGroup();

            resourceRequirements.getAllResourceNames().forEach(requirement -> {

                // get the item based upon the block we are looking for
                Block block = Block.getBlockFromName(requirement);
                Item item = ItemBlock.getItemFromBlock(block);

                itemGroup.add(item, resourceRequirements.getRequirement(requirement));
            });
    }

    public ItemRequest(BlockPos dropoffLocation, ItemGroup itemGroup){
        this.dropoffLocation = dropoffLocation;
        this.itemGroup = itemGroup;
    }

    public BlockPos getDropoffLocation(){
        return this.dropoffLocation;
    }

    public Collection<Item> getAllItems(){
        return itemGroup.getAllItems();
    }

    public int getNumberOfRequestedItem(Item item){
        return itemGroup.getNumberOfItem(item);
    }

}
