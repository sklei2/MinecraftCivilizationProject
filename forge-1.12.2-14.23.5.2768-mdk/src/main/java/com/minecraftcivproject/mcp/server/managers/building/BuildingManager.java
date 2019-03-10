package com.minecraftcivproject.mcp.server.managers.building;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.BlueprintManager;

public class BuildingManager {

    private final BlueprintManager blueprintManager;
    private final ConstructionManager constructionManager;
    private final OperableBuildingManager operableBuildingManager;

    public BuildingManager(){
        this(new BlueprintManager(), new ConstructionManager(), new OperableBuildingManager());
    }

    public BuildingManager(BlueprintManager blueprintManager, ConstructionManager constructionManager, OperableBuildingManager operableBuildingManager){
        this.blueprintManager = blueprintManager;
        this.constructionManager = constructionManager;
        this.operableBuildingManager = operableBuildingManager;
    }
}
