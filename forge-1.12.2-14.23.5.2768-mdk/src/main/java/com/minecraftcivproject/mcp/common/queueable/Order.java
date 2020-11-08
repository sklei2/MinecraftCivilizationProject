package com.minecraftcivproject.mcp.common.queueable;


import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.*;


public class Order {

    public Map<Block, Integer> blockMap;
    private Map<Item, Integer> itemMap;
    private Map<Item, Integer> bank;
    public Item currentItem;
    boolean isItemOrder;
    IBlockState state;


    public Order(Map<Block, Integer> map) {
        this.blockMap = map;
        this.itemMap = this.convertToItemMap(map);
        this.bank = new HashMap<>();  // This needs to be initialized to a new HashMap or it will be saved as a null pointer
        this.initializeBank();
    }

    public Order(Map<Item, Integer> map, boolean isItemOrder) {
        this.itemMap = map;
        this.isItemOrder = isItemOrder;
        this.bank = new HashMap<>();
        this.initializeBank();
    }


    public int getQuantity(Item item) {
        return this.itemMap.get(item);
    }

    public Set<Block> getBlockList() {
        return this.blockMap.keySet();
    }

    public Set<Item> getItemList() {
        return this.itemMap.keySet();
    }

    public boolean isFilled() {
        int size = this.itemMap.size();
        Set<Item> itemList = this.getItemList();
        Iterator<Item> itr = itemList.iterator();  // THIS NEEDS TO KNOW WHAT BLOCK TO GET WHAT ITEM FROM WHAT BLOCK

        int cnt = 0;
        for (int i = 0; i < size; i++) {
            this.currentItem = itr.next();
            if (this.bank.get(this.currentItem) == this.getQuantity(this.currentItem)) {
                cnt++;
            }
        }
        return cnt == size;
    }

    public boolean isFilled(ArrayList<Boolean> checkList) {
        int size = checkList.size();
        int trueCnt = 0;
        for (Boolean aBoolean : checkList) {  // This is an enhanced for loop... i = 0; i < size; i++
            if (aBoolean) {
                trueCnt++;
            }
        }
        return trueCnt == size;
    }

    public boolean resourceSatisfied(Item item) {
        return false;
    }

    public boolean resourceSatisfied(Block block) {

        return false;
    }

    public void add(Item item, Integer quantity) {
        this.bank.put(item, quantity);  // Will overwrite the quantity of the same input key, thus "adding" the resource to the internal bank of the order
    }

    private Map<Item, Integer> convertToItemMap(Map<Block, Integer> map) {
        Map<Item, Integer> itemMap = new HashMap<>();
        for (int i = 0; i < map.size(); i++) {
            Block block = map.keySet().iterator().next();
            Item item = block.getItemDropped(state, new Random(), 0);
            Integer itemQty = map.get(block);
            itemMap.put(item, itemQty);
        }
        return itemMap;
    }

    private void initializeBank() {
        int size = this.itemMap.size();
        Set<Item> itemList = this.getItemList();
        for (int i = 0; i < size; i++) {
            this.bank.put(itemList.iterator().next(), 0);
        }
    }

}
