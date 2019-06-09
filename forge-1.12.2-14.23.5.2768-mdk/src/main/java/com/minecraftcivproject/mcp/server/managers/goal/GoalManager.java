package com.minecraftcivproject.mcp.server.managers.goal;

import com.minecraftcivproject.mcp.server.managers.building.BuildingManager;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBlueprint;
import com.minecraftcivproject.mcp.server.managers.resource.ResourceManager;
import com.minecraftcivproject.mcp.server.managers.villager.VillagerManager;
import net.minecraft.util.math.BlockPos;

public class GoalManager {

    private final BuildingManager buildingManager;
    private final ResourceManager resourceManager;
    private final VillagerManager villagerManager;

    public GoalManager(TownBlueprint townBlueprint, BlockPos blockPos){
        this(new BuildingManager(townBlueprint, blockPos), new ResourceManager(), new VillagerManager());
    }

    public GoalManager(BuildingManager buildingManager, ResourceManager resourceManager, VillagerManager villagerManager){
        this.buildingManager = buildingManager;
        this.resourceManager = resourceManager;
        this.villagerManager = villagerManager;
    }

    public void buildTown(){

    }
}
