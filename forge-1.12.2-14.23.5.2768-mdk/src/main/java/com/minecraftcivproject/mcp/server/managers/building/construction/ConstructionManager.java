package com.minecraftcivproject.mcp.server.managers.building.construction;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBuildingBlueprint;
import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;
import com.minecraftcivproject.mcp.server.managers.queue.TribeQueue;
import com.minecraftcivproject.mcp.server.managers.queue.TribeQueueEnum;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ui.tribe.queuedisplay.construction.ConstructionProjectDisplayer;

import java.util.*;

public class ConstructionManager extends Observable implements Observer{

    private final World world;
    private final BlockPos corner;
    private final TribeQueue<ConstructionProject> constructionProjectQueue;

    public ConstructionManager(QueueManager queueManager, World world, BlockPos blockPos){
        this.world = world;
        this.constructionProjectQueue = new TribeQueue<>(new LinkedList<>(), TribeQueueEnum.CONSTRUCTION, new ConstructionProjectDisplayer());
        queueManager.addQueue(this.constructionProjectQueue);
        corner = blockPos;
        this.addObserver(queueManager);
    }

    public void queue(TownBuildingBlueprint townBuildingBlueprint){
        ConstructionProject constructionProject = new ConstructionProject(townBuildingBlueprint, world, corner);
        constructionProject.addObserver(this);

        constructionProjectQueue.queue(constructionProject);
    }

    public void queueAll(List<TownBuildingBlueprint> buildings){
        buildings.forEach(this::queue);
    }

    @Override
    public void update(Observable observable, Object o) {
        System.out.println("Construction manager " + this + " saw an update. Notify observers");
        setChanged();
        notifyObservers();
    }
}
