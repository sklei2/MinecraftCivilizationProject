package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;

public class ResourceInventoryListener implements IInventoryChangedListener {

    private Runnable callback;

    public ResourceInventoryListener(Runnable callback){
        this.callback = callback;
    }

    @Override
    public void onInventoryChanged(IInventory invBasic) {
        System.out.println("inventory updated");

        this.callback.run();
    }
}
