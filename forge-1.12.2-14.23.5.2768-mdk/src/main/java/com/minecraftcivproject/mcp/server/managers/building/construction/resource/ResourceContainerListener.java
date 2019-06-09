package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.function.Consumer;

public class ResourceContainerListener implements IContainerListener {

    Consumer<NonNullList<ItemStack>> callback;

    public ResourceContainerListener(Consumer<NonNullList<ItemStack>> callback){
        this.callback = callback;
    }

    @Override
    public void sendAllContents(Container containerToSend, NonNullList<ItemStack> itemsList) {
        callback.accept(itemsList);
    }

    @Override
    public void sendSlotContents(Container containerToSend, int slotInd, ItemStack stack) {

    }

    @Override
    public void sendWindowProperty(Container containerIn, int varToUpdate, int newValue) {

    }

    @Override
    public void sendAllWindowProperties(Container containerIn, IInventory inventory) {

    }
}
