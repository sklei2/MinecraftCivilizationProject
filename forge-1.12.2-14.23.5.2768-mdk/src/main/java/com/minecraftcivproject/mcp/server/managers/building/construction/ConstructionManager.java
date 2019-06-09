package com.minecraftcivproject.mcp.server.managers.building.construction;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBuildingBlueprint;
import net.minecraft.util.math.BlockPos;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConstructionManager {

    private final Queue<ConstructionProject> constructionQueue;
    private final BlockPos corner;

    public ConstructionManager(BlockPos blockPos){
        constructionQueue = new LinkedList<>();
        corner = blockPos;
    }

    public void queue(TownBuildingBlueprint townBuildingBlueprint){
        constructionQueue.add(new ConstructionProject(townBuildingBlueprint, corner));
    }

    public void queueAll(List<TownBuildingBlueprint> buildings){
        buildings.forEach(this::queue);
    }
}
