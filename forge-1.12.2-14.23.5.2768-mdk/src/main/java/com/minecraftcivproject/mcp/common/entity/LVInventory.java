package com.minecraftcivproject.mcp.common.entity;


import com.minecraftcivproject.mcp.server.managers.resource.ItemGroup;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;

public class LVInventory extends InventoryBasic {

    public LVInventory(String name, boolean isCustomName, int maxSlots) {
        super(name, isCustomName, maxSlots);
    }

    public ItemGroup getInventoryItems(){
        ItemGroup itemGroup = new ItemGroup();

        int size = this.getSizeInventory();
        for (int i = 0; i < size; ++i) {
            Item item = this.getStackInSlot(i).getItem();
            int count = this.getStackInSlot(i).getCount();

            itemGroup.add(item, count);
        }

        return itemGroup;
    }

//    public int findAll(Item item) {
//        int size = this.getSizeInventory();
//        int cnt = 0;
//        for (int i = 0; i == size; i++) {
//            if (this.getStackInSlot(i).getBlock() == item) {
//                cnt++;
//            }
//        }
//        return cnt;
//    }
//
//    public ArrayList<Integer> getEmptySlots() {
//        ArrayList<Integer> array = new ArrayList<>();
//        int size = this.getSizeInventory();
//        for (int i = 0; i <= size; i++) {
//            if (!(this.getStackInSlot(i) == ItemStack.EMPTY)) {
//                array.add(i);
//            }
//        }
//        return array;
//    }
}
