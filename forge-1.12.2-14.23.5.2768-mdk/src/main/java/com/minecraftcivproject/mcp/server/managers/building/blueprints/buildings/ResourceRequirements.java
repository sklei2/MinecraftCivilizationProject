package com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ResourceRequirements {

    private final Collection<ResourceRequirement> resourceRequirements;

    public ResourceRequirements(Collection<ResourceRequirement> resourceRequirements){
        this.resourceRequirements = resourceRequirements;
    }

    public int getRequirement(String resource){
        for(ResourceRequirement resourceRequirement : resourceRequirements){
            if(resourceRequirement.getResource().equals(resource)){
                return resourceRequirement.getRequired();
            }
        }
        throw new IllegalStateException("There is no resource " + resource + " for this resource requirement");
    }

    public List<String> getRequirements(){
        return resourceRequirements.stream().map(ResourceRequirement::getResource).collect(Collectors.toList());
    }
}
