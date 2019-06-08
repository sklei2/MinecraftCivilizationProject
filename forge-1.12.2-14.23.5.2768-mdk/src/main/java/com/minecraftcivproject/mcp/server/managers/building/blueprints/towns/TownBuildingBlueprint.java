package com.minecraftcivproject.mcp.server.managers.building.blueprints.towns;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.Blueprint;

public class TownBuildingBlueprint {
    private Blueprint buildingBlueprint;
    private int startRow;
    private int startCol;

    public TownBuildingBlueprint(Blueprint buildingBlueprint, int startRow, int startCol){
        this.buildingBlueprint = buildingBlueprint;
        this.startRow = startRow;
        this.startCol = startCol;
    }

    public Blueprint getBuildingBlueprint() {
        return buildingBlueprint;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }
}
