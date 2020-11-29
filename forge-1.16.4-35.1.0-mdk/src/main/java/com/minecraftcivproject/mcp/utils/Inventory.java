package com.minecraftcivproject.mcp.utils;

import com.minecraftcivproject.mcp.server.managers.resource.ItemGroup;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory extends InventoryBasic {
    public Inventory(String title, boolean customName, int slotCount) {
        super(title, customName, slotCount);
    }

    public ItemGroup asItemGroup(){
        ItemGroup itemGroup = new ItemGroup();

        for(int i = 0; i < getSizeInventory(); ++i){
            ItemStack itemStack = getStackInSlot(i);

            if(!itemStack.isEmpty()){
                itemGroup.add(itemStack.getItem(), itemStack.getCount());
            }
        }

        return itemGroup;
    }

    public boolean hasItems(ItemGroup itemGroup){
        ItemGroup currentInventory = asItemGroup();

        for(Item item : itemGroup.getAllItems()){
            if(currentInventory.getNumberOfItem(item) < itemGroup.getNumberOfItem(item)){
                return false;
            }
        }

        return true;
    }

    public ItemGroup removeItems(ItemGroup itemsToRemove){
        ItemGroup itemsRemaining = itemsToRemove.copy();

        for(Item item : itemsRemaining.getAllItems()){
            int leftover = remove(item, itemsRemaining.getNumberOfItem(item));
            itemsRemaining.set(item, leftover);
        }

        return itemsRemaining;
    }

    public ItemGroup add(ItemGroup itemGroup){
        ItemGroup itemsRemaining = itemGroup.copy();

        for(Item item : itemsRemaining.getAllItems()){
            int leftover = add(item, itemsRemaining.getNumberOfItem(item));
            itemsRemaining.set(item, leftover);
        }

        return itemsRemaining;
    }

    public int add(Item item, int count){
        int leftover = placeItemsInInventory(item, count);
        markDirty();

        return leftover;
    }

    public int remove(Item item, int count){
        int leftover = removeItemsInInventory(item, count);
        markDirty();

        return leftover;
    }

    public int getCount(Item item){
        Map<String, Integer> inventory = flattenInventory();
        return inventory.getOrDefault(item.getUnlocalizedName(), 0);
    }

    private Map<String, Integer> flattenInventory(){
        Map<String, Integer> itemCounts = new HashMap<>();
        List<ItemStack> allStacks = getContents();

        for(ItemStack itemStack : allStacks){
            String name = itemStack.getItem().getUnlocalizedName();
            int currentCount = itemCounts.getOrDefault(name, 0);

            itemCounts.put(name, currentCount + itemStack.getCount());
        }

        return itemCounts;
    }

    private List<ItemStack> splitIntoStacks(Item item, int count){
        List<ItemStack> itemStacks = new ArrayList<>();

        ItemStack sampleItemStack = new ItemStack(item);
        int maxSize = sampleItemStack.getMaxStackSize();

        int fullStacks = count/maxSize;
        int leftover = count % maxSize;

        for(int numCreatedFullStacks = 0; numCreatedFullStacks < fullStacks; ++ numCreatedFullStacks){
            itemStacks.add(new ItemStack(item, maxSize));
        }

        itemStacks.add(new ItemStack(item, leftover));

        return itemStacks;
    }

    private List<ItemStack> getContents(){
        int slots = getSizeInventory();

        List<ItemStack> itemStacks = new ArrayList<>();
        for(int i = 0; i < slots; ++i){
            itemStacks.add(getStackInSlot(i));
        }
        return itemStacks;
    }

    private int removeItemsInInventory(Item item, int count){

        int countToRemove = count;

        //go backwards
        for(int i = getSizeInventory() - 1; i >= 0; --i){

            ItemStack stackInSlot = getStackInSlot(i);

            //nothing here to remove
            if(stackInSlot.isEmpty() || !stackInSlot.getItem().equals(item)){
                continue;
            }

            countToRemove = removeItemInStack(stackInSlot, countToRemove);
        }

        return countToRemove;
    }

    private int removeItemInStack(ItemStack itemStack, int countToRemove){
        int itemCount = itemStack.getCount();

        if(countToRemove > itemCount){
            itemStack.setCount(0);
        }

        return countToRemove - itemCount;
    }

    private int placeItemsInInventory(Item item, int count){
        List<ItemStack> itemStacks = splitIntoStacks(item, count);

        int leftover = 0;

        for(ItemStack itemStack : itemStacks){
            leftover += placeStackInInventory(itemStack);
        }

        return leftover;
    }

    private int placeStackInInventory(ItemStack itemStack){

        while(itemStack.getCount() > 0){
            int nextAvailableInventorySlot = getNextAvailableSlot(itemStack);

            // no room left, return the leftover
            if(nextAvailableInventorySlot == -1){
                return itemStack.getCount();
            }

            int leftover = placeItemInSlot(itemStack.getItem(), itemStack.getCount(), nextAvailableInventorySlot);
            itemStack.setCount(leftover);
        }

        return 0;
    }

    private int getNextAvailableSlot(ItemStack itemStack){
        for(int i = 0; i < getSizeInventory(); ++i){
            ItemStack existingStack = getStackInSlot(i);

            // is the slot empty
            if(existingStack.isEmpty()){
                return i;
            }

            // is it the same item and is there room in the stack
            if(existingStack.getItem() == itemStack.getItem()
                    && existingStack.getCount() < existingStack.getMaxStackSize()){
                return i;
            }
        }

        // no available slot
        return -1;
    }

    private int placeItemInSlot(Item item, int count, int slot){
        ItemStack existingStack = getStackInSlot(slot);
        ItemStack currentStack = new ItemStack(item, count);

        //nothing is in this slot
        if(existingStack.isEmpty()){
            setInventorySlotContents(slot, currentStack);
            return 0;
        }

        // could not place any blocks in this slot
        if(!existingStack.getItem().equals(item)){
            return count;
        }

        int leftover = 0;

        //and this slot has room
        if(existingStack.getMaxStackSize() < existingStack.getCount()){
            //determine how many items we can place in this stack
            int maxItemsToPlace = existingStack.getMaxStackSize() - existingStack.getCount();
            int itemsToPlace = currentStack.getCount();

            if(maxItemsToPlace < currentStack.getCount()){
                itemsToPlace = maxItemsToPlace;
                leftover = currentStack.getCount() - maxItemsToPlace;
            }

            //place the items
            setInventorySlotContents(slot, new ItemStack(item, itemsToPlace));
        }

        return leftover;
    }
}
