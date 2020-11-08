package com.minecraftcivproject.mcp.server.managers.resource;

import net.minecraft.item.Item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ItemGroup {
    private final Map<Item, Integer> itemGroup = new HashMap<>();

    public void add(Item item, int numberToAdd){
        int currentNumber = itemGroup.getOrDefault(item, 0);

        itemGroup.put(item, currentNumber + numberToAdd);
    }

    public void subtract(Item item, int numberToSubtract){
        int currentNumber = itemGroup.getOrDefault(item, 0);
        int difference = currentNumber - numberToSubtract;

        // no negatives
        if(difference < 0){
            difference = 0;
        }

        itemGroup.put(item, difference);
    }

    public void set(Item item, int numberToSetTo){
        itemGroup.put(item, numberToSetTo);
    }

    public Collection<Item> getAllItems(){
        return itemGroup.keySet();
    }

    public int getNumberOfItem(Item item){
        return itemGroup.getOrDefault(item, 0);
    }

    public boolean isEmpty(){
        for(Item item : itemGroup.keySet()){
            int numberOfItem = itemGroup.getOrDefault(item, 0);

            if (numberOfItem > 0){
                return false;
            }
        }

        return true;
    }

    public ItemGroup copy(){
        ItemGroup itemGroup = new ItemGroup();

        for(Item item : this.getAllItems()){
            itemGroup.add(item, this.getNumberOfItem(item));
        }

        return itemGroup;
    }


    public ItemGroup minus(ItemGroup other){
        ItemGroup differenceItemGroup = copy();

        for(Item otherItem : other.getAllItems()){
            int otherItemQuantity = other.getNumberOfItem(otherItem);
            differenceItemGroup.subtract(otherItem, otherItemQuantity);
        }

        return differenceItemGroup;
    }
}