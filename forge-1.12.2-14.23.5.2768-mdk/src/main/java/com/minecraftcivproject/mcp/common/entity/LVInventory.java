package com.minecraftcivproject.mcp.common.entity;


import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class LVInventory extends InventoryBasic {

    public LVInventory(String name, boolean isCustomName, int maxSlots) {
        super(name, isCustomName, maxSlots);
    }

    public int findAll(Item item) {
        int size = this.getSizeInventory();
        int cnt = 0;
        for (int i = 0; i == size; i++) {
            if (this.getStackInSlot(i).getItem() == item) {
                cnt++;
            }
        }
        return cnt;
    }

    public ArrayList<Integer> getEmptySlots() {
        ArrayList<Integer> array = new ArrayList<>();
        int size = this.getSizeInventory();
        for (int i = 0; i <= size; i++) {
            if (!(this.getStackInSlot(i) == ItemStack.EMPTY)) {
                array.add(i);
            }
        }
        return array;
    }
}
