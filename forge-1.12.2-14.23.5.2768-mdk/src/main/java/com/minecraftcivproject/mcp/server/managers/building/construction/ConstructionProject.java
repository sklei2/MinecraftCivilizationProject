package com.minecraftcivproject.mcp.server.managers.building.construction;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.ResourceRequirements;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBuildingBlueprint;
import com.minecraftcivproject.mcp.server.managers.building.construction.resource.ResourceBin;
import com.minecraftcivproject.mcp.server.managers.queue.Queueable;
import com.minecraftcivproject.mcp.utils.BlockUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Observable;
import java.util.Observer;

public class ConstructionProject extends Observable implements Observer, Queueable {

    private TownBuildingBlueprint townBuildingBlueprint;
    private ResourceBin resourceBin;
    private World world;
    private BlockPos baseLocation;

    public ConstructionProject(TownBuildingBlueprint blueprint, World world, BlockPos baseLocation){
        this.world = world;
        this.townBuildingBlueprint = blueprint;
        this.baseLocation = baseLocation;

        this.resourceBin = new ResourceBin(blueprint.getResourceRequirements(), this::completeProject);
        this.resourceBin.addObserver(this);

        BlockPos binBlockPos = baseLocation.add(townBuildingBlueprint.getStartRow(),0, townBuildingBlueprint.getStartCol());
        BlockUtils.placeBlock(world, binBlockPos, resourceBin.getResourceBinBlock());
    }

    public void addResource(String resource, int count){
        this.resourceBin.add(resource, count);

        if(resourceBin.isFull()){
            completeProject();
        }
    }

    public int getResourceCount(String resource){
        return this.resourceBin.get(resource);
    }

    public String getName(){
        return this.townBuildingBlueprint.getName();
    }

    public ResourceRequirements getResourceRequirements(){
        return this.resourceBin.getResourceRequirements();
    }

    private void completeProject(){
        townBuildingBlueprint.apply(world, baseLocation);
    }

    @Override
    public void update(Observable observable, Object o) {
        System.out.println("Construction project " + this + " saw an update. Notify observers");
        setChanged();
        notifyObservers();
    }
}
