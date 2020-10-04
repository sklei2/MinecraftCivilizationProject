package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

public class ResourceBinInventory extends InventoryBasic {

    private final Runnable updatedCallback;

    public ResourceBinInventory(Runnable updatedCallback){
        super("Test", false, 27);
        this.updatedCallback = updatedCallback;
    }

    public void contentsUpdated(){
        updatedCallback.run();
    }

    @Override
    public void markDirty(){
        contentsUpdated();
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

    private Queue<ItemStack> splitIntoStacks(String itemName, int count){
        LinkedList<ItemStack> itemStacks = new LinkedList<>();

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
        Queue<ItemStack> itemStacks = splitIntoStacks(item.getUnlocalizedName(), count);
        ItemStack currentStack = itemStacks.remove();

        int leftover = 0;

        for(int i = 0; i < getSizeInventory(); ++i){

            leftover = placeItemInSlot(currentStack.getItem(), currentStack.getCount(), i);
            currentStack.setCount(leftover);

            //check if there are any more stacks to place
            if(currentStack.getCount() == 0){
                currentStack = itemStacks.remove();

                if(currentStack == null){
                    return 0;
                }
            }
        }

        int totalLeftover = getQueuedItems(itemStacks) + leftover;
        return totalLeftover;
    }

    private int getQueuedItems(Queue<ItemStack> queuedItems){
        int queued = 0;
        ItemStack itemStack = queuedItems.remove();

        while(itemStack != null){
            queued += itemStack.getCount();
            itemStack = queuedItems.remove();
        }

        return queued;
    }

    private int placeItemInSlot(Item item, int count, int slot){
        ItemStack existingStack = getStackInSlot(slot);
        ItemStack currentStack = new ItemStack(item, count);

        int leftover = 0;

        //nothing is in this slot
        if(existingStack.isEmpty()){
            setInventorySlotContents(slot, currentStack);
            return leftover;
        }

        //the same item is in this slot
        if(existingStack.getItem().equals(item)){

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
        }

        return leftover;
    }
}
