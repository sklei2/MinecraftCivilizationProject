package com.minecraftcivproject.mcp.server.managers.building;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.BlueprintManager;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBlueprint;
import com.minecraftcivproject.mcp.server.managers.building.construction.ConstructionManager;

public class BuildingManager {

    private final BlueprintManager blueprintManager;
    private final ConstructionManager constructionManager;
    private final OperableBuildingManager operableBuildingManager;

    public BuildingManager(TownBlueprint townBlueprint){
        this(new BlueprintManager(townBlueprint), new ConstructionManager(), new OperableBuildingManager());
    }

    public BuildingManager(BlueprintManager blueprintManager, ConstructionManager constructionManager, OperableBuildingManager operableBuildingManager){
        this.blueprintManager = blueprintManager;
        this.constructionManager = constructionManager;
        this.operableBuildingManager = operableBuildingManager;

        this.constructionManager.queueAll(this.blueprintManager.getBuildingsToConstruct());
    }

}
