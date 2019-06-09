package com.minecraftcivproject.mcp.server.managers.building.construction;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.ResourceRequirements;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBuildingBlueprint;
import com.minecraftcivproject.mcp.server.managers.building.construction.resource.ResourceBin;
import com.minecraftcivproject.mcp.utils.BlockUtils;
import net.minecraft.util.math.BlockPos;

public class ConstructionProject {

    private TownBuildingBlueprint townBuildingBlueprint;
    private ResourceBin resourceBin;
    private BlockPos baseLocation;

    public ConstructionProject(TownBuildingBlueprint blueprint, BlockPos baseLocation){
        this.townBuildingBlueprint = blueprint;
        this.baseLocation = baseLocation;

        this.resourceBin = new ResourceBin(blueprint.getResourceRequirements(), this::completeProject);

        BlockPos binBlockPos = baseLocation.add(townBuildingBlueprint.getStartRow(),0, townBuildingBlueprint.getStartCol());
        BlockUtils.placeBlock(binBlockPos, resourceBin);
    }

    public void addResource(String resource, int count){
        this.resourceBin.add(resource, count);

        if(resourceBin.isFull()){
            completeProject();
        }
    }

    public ResourceRequirements getResourceRequirements(){
        return this.resourceBin.getResourceRequirements();
    }

    private void completeProject(){
        townBuildingBlueprint.apply(baseLocation);
    }

}
