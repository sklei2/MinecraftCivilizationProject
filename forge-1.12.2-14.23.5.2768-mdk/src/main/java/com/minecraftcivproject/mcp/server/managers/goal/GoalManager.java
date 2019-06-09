package com.minecraftcivproject.mcp.server.managers.goal;

import com.minecraftcivproject.mcp.server.managers.building.BuildingManager;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBlueprint;
import com.minecraftcivproject.mcp.server.managers.resource.ResourceManager;
import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;
import com.minecraftcivproject.mcp.server.managers.villager.VillagerManager;
import net.minecraft.util.math.BlockPos;

public class GoalManager {

    private final BuildingManager buildingManager;
    private final ResourceManager resourceManager;
    private final VillagerManager villagerManager;
    private final QueueManager queueManager;

    public GoalManager(QueueManager queueManager, TownBlueprint townBlueprint, BlockPos blockPos){
        this(new BuildingManager(queueManager, townBlueprint, blockPos), new ResourceManager(queueManager), new VillagerManager(queueManager), queueManager);
    }

    public GoalManager(BuildingManager buildingManager, ResourceManager resourceManager, VillagerManager villagerManager, QueueManager queueManager){
        this.buildingManager = buildingManager;
        this.resourceManager = resourceManager;
        this.villagerManager = villagerManager;
        this.queueManager = queueManager;
    }

    public void buildTown(){

    }
}
