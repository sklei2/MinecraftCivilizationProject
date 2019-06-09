package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.NonNullList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceBinTileEntity extends TileEntityChest {

    private Runnable updateCallback;
    private ContainerChest containerChest;

    public ResourceBinTileEntity(Runnable updateCallback){
        this.updateCallback = updateCallback;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        this.fillWithLoot(playerIn);
        this.containerChest = new ContainerChest(playerInventory, this, playerIn);
        containerChest.addListener(new ResourceContainerListener(this::contentsUpdated));

        return containerChest;
    }

    public void contentsUpdated(NonNullList<ItemStack> itemStacks){
        updateCallback.run();
    }

    public void add(Item item, int count){
        Map<String, Integer> inventory = flattenInventory();
        int currentCount = inventory.getOrDefault(item.getUnlocalizedName(), 0);

        inventory.put(item.getUnlocalizedName(), count + currentCount);

        List<ItemStack> expandedInventory = expandInventory(inventory);

        containerChest.setAll(expandedInventory);
    }

    public void remove(Item item, int count){
        Map<String, Integer> inventory = flattenInventory();
        int currentCount = inventory.getOrDefault(item.getUnlocalizedName(), 0);
        int newCount = currentCount - count;

        if(newCount < 0){
            throw new IllegalStateException("Tried to remove " + count + " " + item + " when only " + currentCount + " are left");
        }

        inventory.put(item.getUnlocalizedName(), currentCount - count);

        List<ItemStack> expandedInventory = expandInventory(inventory);

        containerChest.setAll(expandedInventory);
    }

    public int getCount(Item item){
        Map<String, Integer> inventory = flattenInventory();
        return inventory.getOrDefault(item.getUnlocalizedName(), 0);
    }

    private Map<String, Integer> flattenInventory(){
        Map<String, Integer> itemCounts = new HashMap<>();
        List<ItemStack> allStacks = containerChest.getInventory();

        for(ItemStack itemStack : allStacks){
            String name = itemStack.getItem().getUnlocalizedName();
            int currentCount = itemCounts.getOrDefault(name, 0);

            itemCounts.put(name, currentCount + itemStack.getCount());
        }

        return itemCounts;
    }

    private List<ItemStack> expandInventory(Map<String, Integer> inventory){
        List<ItemStack> itemStacks = new ArrayList<>();

        for(String itemName : inventory.keySet()){
            int count = inventory.get(itemName);
            itemStacks.addAll(splitIntoStacks(itemName, count));
        }

        return itemStacks;
    }

    private List<ItemStack> splitIntoStacks(String itemName, int count){
        List<ItemStack> itemStacks = new ArrayList<>();

        Item i = Item.getByNameOrId(itemName);
        ItemStack sampleItemStack = new ItemStack(i);
        int maxSize = sampleItemStack.getMaxStackSize();

        int fullStacks = count/maxSize;
        int leftover = count % maxSize;

        for(int numCreatedFullStacks = 0; numCreatedFullStacks < fullStacks; ++ numCreatedFullStacks){
            itemStacks.add(new ItemStack(i, maxSize));
        }

        itemStacks.add(new ItemStack(i, leftover));

        return itemStacks;
    }
}
