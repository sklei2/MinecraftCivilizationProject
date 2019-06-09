package com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBlueprint;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBuildingBlueprint;

import java.util.List;

public class BlueprintManager {

    private final TownBlueprint townBlueprint;

    public BlueprintManager(TownBlueprint townBlueprint){
        this.townBlueprint = townBlueprint;
    }

    public List<TownBuildingBlueprint> getBuildingsToConstruct(){
        return townBlueprint.getBuildings();
    }

}
