package com.minecraftcivproject.mcp.server.managers.building.construction;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBuildingBlueprint;
import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;
import com.minecraftcivproject.mcp.server.managers.queue.TribeQueue;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class ConstructionManager {

    private final QueueManager queueManager;
    private final BlockPos corner;


    public ConstructionManager(QueueManager queueManager, BlockPos blockPos){
        this.queueManager = queueManager;
        this.queueManager.addQueue(TribeQueue.CONSTRUCTION, ConstructionProject.class);
        corner = blockPos;
    }

    public void queue(TownBuildingBlueprint townBuildingBlueprint){
        queueManager.queue(TribeQueue.CONSTRUCTION, new ConstructionProject(townBuildingBlueprint, corner));
    }

    public void queueAll(List<TownBuildingBlueprint> buildings){
        buildings.forEach(this::queue);
    }
}
