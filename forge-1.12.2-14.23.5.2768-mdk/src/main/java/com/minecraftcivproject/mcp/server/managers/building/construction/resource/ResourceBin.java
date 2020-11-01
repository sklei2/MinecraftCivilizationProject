package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.ResourceRequirements;
import net.minecraft.item.Item;
import registry.ResourceBinInventoryRegistry;

import java.util.Observable;

public class ResourceBin extends Observable {

    private ResourceRequirements resourceRequirements;
    private ResourceBinBlock resourceBinBlock;
    private Runnable fullCallback;
    private final String id;

    public ResourceBin(ResourceRequirements resourceRequirements, Runnable runnable){
        this.resourceRequirements = resourceRequirements;
        this.resourceBinBlock = new ResourceBinBlock();
        this.fullCallback = runnable;

        // because this can be created both by placement and automatedly
        this.id = this.resourceBinBlock.getId();

        ResourceBinInventoryRegistry.subscribe(id, this::onUpdate);
    }

    public ResourceRequirements getResourceRequirements() {
        return resourceRequirements;
    }


    public boolean isFull(){
        for(String resource : resourceRequirements.getRequirements()){
            int current = getInventory().getCount(Item.getByNameOrId(resource));
            int required = resourceRequirements.getRequirement(resource);

            if(current < required){
                return false;
            }
        }

        return true;
    }

    public int add(Item i, int count){
        return getInventory().add(i, count);
    }

    public int add(String name, int count){
        return add(Item.getByNameOrId(name), count);
    }

    public int remove(Item i, int count){
        return getInventory().remove(i, count);
    }

    public int get(Item i){
        return getInventory().getCount(i);
    }

    public int get(String name){
        return get(Item.getByNameOrId(name));
    }

    public ResourceBinBlock getResourceBinBlock(){
        return resourceBinBlock;
    }

    public void onUpdate(){
        if(isFull()){
            this.fullCallback.run();
        }

        System.out.println("Resource bin " + id + " has been updated " + countObservers());
        setChanged();
        notifyObservers();
    }

    private ResourceBinInventory getInventory(){
        return ResourceBinInventoryRegistry.get(id);
    }
}
