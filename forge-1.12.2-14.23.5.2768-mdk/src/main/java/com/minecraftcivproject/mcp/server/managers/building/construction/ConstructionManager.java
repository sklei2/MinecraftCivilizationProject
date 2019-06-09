package com.minecraftcivproject.mcp.server.managers.building.construction;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBuildingBlueprint;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class ConstructionManager {

    private final Queue<ConstructionProject> constructionQueue;

    public ConstructionManager(){
        constructionQueue = new PriorityQueue<>();
    }

    public void queue(TownBuildingBlueprint townBuildingBlueprint){
        constructionQueue.add(new ConstructionProject(townBuildingBlueprint));
    }

    public void queueAll(List<TownBuildingBlueprint> buildings){
        buildings.forEach(this::queue);
    }
}
