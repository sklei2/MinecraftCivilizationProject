package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.ResourceRequirements;
import net.minecraft.item.Item;

public class ResourceBin {

    private ResourceRequirements resourceRequirements;
    private ResourceBinBlock resourceBinBlock;
    private Runnable fullCallback;

    public ResourceBin(ResourceRequirements resourceRequirements, Runnable runnable){
        this.resourceRequirements = resourceRequirements;
        this.resourceBinBlock = new ResourceBinBlock(this::onUpdate);
        this.fullCallback = runnable;
    }

    public ResourceRequirements getResourceRequirements() {
        return resourceRequirements;
    }


    public boolean isFull(){
        for(String resource : resourceRequirements.getRequirements()){
            int current = resourceBinBlock.get(resource);
            int required = resourceRequirements.getRequirement(resource);

            if(current < required){
                return false;
            }
        }

        return true;
    }

    public int add(Item i, int count){
        return this.resourceBinBlock.add(i, count);
    }

    public int add(String name, int count){
        return add(Item.getByNameOrId(name), count);
    }

    public int remove(Item i, int count){
        return this.resourceBinBlock.remove(i, count);
    }

    public int get(Item i){
        return this.resourceBinBlock.get(i);
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
    }
}
