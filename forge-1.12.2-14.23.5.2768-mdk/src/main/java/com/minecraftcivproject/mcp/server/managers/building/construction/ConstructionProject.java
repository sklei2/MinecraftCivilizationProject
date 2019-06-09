package com.minecraftcivproject.mcp.server.managers.building.construction;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBuildingBlueprint;
import com.minecraftcivproject.mcp.server.managers.building.construction.resource.ResourceBin;

import java.util.List;

public class ConstructionProject {

    private TownBuildingBlueprint townBuildingBlueprint;
    private List<String> requiredResources;
    private ResourceBin resourceBin;

    public ConstructionProject(TownBuildingBlueprint blueprint){
        this.townBuildingBlueprint = blueprint;
        this.resourceBin = new ResourceBin(blueprint.getResourceRequirements());
    }

}
