package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.ResourceRequirements;

import java.util.HashMap;
import java.util.Map;

public class ResourceBin {

    private Map<String, Integer> resourceCount;
    private ResourceRequirements resourceRequirements;

    public ResourceBin(ResourceRequirements resourceRequirements, String ... requiredResources){
        resourceCount = new HashMap<>();

        for(String requiredResource: requiredResources){
            resourceCount.put(requiredResource, 0);
        }

        this.resourceRequirements = resourceRequirements;
    }

    public void addToResource(String resource, int count){
        int current = resourceCount.get(resource);
        resourceCount.put(resource, current + count);
    }

    public int getResources(String resource){
        return resourceCount.get(resource);
    }

    public boolean isFull(){
        for(String resource : resourceCount.keySet()){
            int current = resourceCount.get(resource);
            int required = resourceRequirements.getRequirement(resource);

            if(current < required){
                return false;
            }
        }

        return true;
    }
}
