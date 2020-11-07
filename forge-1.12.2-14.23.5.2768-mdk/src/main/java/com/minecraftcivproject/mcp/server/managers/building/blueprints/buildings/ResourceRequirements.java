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
                return resourceRequirement.getNumRequired();
            }
        }
        throw new IllegalStateException("There is no resource " + resource + " for this resource requirement");
    }

    public List<String> getAllResourceNames(){
        return resourceRequirements.stream().map(ResourceRequirement::getResource).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final ResourceRequirements other = (ResourceRequirements) obj;

        return this.getAllResourceNames().equals(other.getAllResourceNames());
    }
}
