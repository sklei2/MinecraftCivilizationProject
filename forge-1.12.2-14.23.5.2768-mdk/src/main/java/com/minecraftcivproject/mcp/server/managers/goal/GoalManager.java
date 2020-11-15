package com.minecraftcivproject.mcp.server.managers.goal;

import com.minecraftcivproject.mcp.server.managers.TickableManager;
import com.minecraftcivproject.mcp.server.managers.building.BuildingManager;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBlueprint;
import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;
import com.minecraftcivproject.mcp.server.managers.resource.ResourceManager;
import com.minecraftcivproject.mcp.server.managers.villager.VillagerManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GoalManager implements TickableManager {

    private final BuildingManager buildingManager;
    private final ResourceManager resourceManager;
    private final VillagerManager villagerManager;
    private final QueueManager queueManager;

    public GoalManager(QueueManager queueManager, TownBlueprint townBlueprint, World world, BlockPos blockPos){
        this(new BuildingManager(queueManager, townBlueprint, world, blockPos), new ResourceManager(queueManager), new VillagerManager(queueManager, world, blockPos), queueManager);
    }

    public GoalManager(BuildingManager buildingManager, ResourceManager resourceManager, VillagerManager villagerManager, QueueManager queueManager){
        this.buildingManager = buildingManager;
        this.resourceManager = resourceManager;
        this.villagerManager = villagerManager;
        this.queueManager = queueManager;
    }

    public void buildTown(){

    }

    @Override
    public void onTick() {
        buildingManager.onTick();
        resourceManager.onTick();
        villagerManager.onTick();
    }
}
