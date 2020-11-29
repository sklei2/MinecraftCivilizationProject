package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import com.minecraftcivproject.mcp.utils.Inventory;
import registry.ResourceBinInventoryRegistry;

public class ResourceBinInventory extends Inventory {

    private final String id;

    public ResourceBinInventory(String id){
        super("Test", false, 27);
        ResourceBinInventoryRegistry.add(id, this);
        this.id = id;
    }

    public void contentsUpdated(){
        ResourceBinInventoryRegistry.trigger(id);
    }

    @Override
    public void markDirty(){
        contentsUpdated();
    }
}
