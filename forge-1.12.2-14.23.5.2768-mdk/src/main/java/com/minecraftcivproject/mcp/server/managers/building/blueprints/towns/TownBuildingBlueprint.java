package com.minecraftcivproject.mcp.server.managers.building.blueprints.towns;

import com.minecraftcivproject.mcp.MinecraftCivProject;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.Blueprint;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.ResourceRequirements;
import net.minecraft.util.math.BlockPos;

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

    public ResourceRequirements getResourceRequirements(){
        return buildingBlueprint.getResourceRequirements();
    }

    public void apply(BlockPos initialPosition){
        BlockPos buildingPosition = initialPosition.add(startRow, 0, startCol);

        this.buildingBlueprint.apply(MinecraftCivProject.getWorld(), buildingPosition);
    }
}
