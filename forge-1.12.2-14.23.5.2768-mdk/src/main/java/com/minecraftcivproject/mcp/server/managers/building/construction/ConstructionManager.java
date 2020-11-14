package com.minecraftcivproject.mcp.server.managers.building.construction;

import com.minecraftcivproject.mcp.server.managers.TickableManager;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBuildingBlueprint;
import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;
import com.minecraftcivproject.mcp.server.managers.resource.ItemRequest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ConstructionManager extends Observable implements Observer, TickableManager {

    private final World world;
    private final BlockPos corner;
    private final QueueManager queueManager;

    public ConstructionManager(QueueManager queueManager, World world, BlockPos blockPos){
        this.world = world;
        corner = blockPos;
        this.queueManager = queueManager;
    }

    public void queue(TownBuildingBlueprint townBuildingBlueprint){
        ConstructionProject constructionProject = new ConstructionProject(townBuildingBlueprint, world, corner);
        constructionProject.addObserver(this);

        queueManager.getConstructionProjectQueue().queue(constructionProject);
        queueManager.getItemRequestQueue().queue(new ItemRequest(constructionProject.getDropoffLocation(), constructionProject.getResourceRequirements()));
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

    @Override
    public void onTick() {

    }
}
