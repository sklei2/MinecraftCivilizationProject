package com.minecraftcivproject.mcp.server.managers.building.blueprints.towns;

import com.google.gson.annotations.Expose;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TownBlueprint {

    //public fields needed for deserialization.
    @Expose
    private String name;

    //notice how BuildingAssignment is defined for deserialization as well
    @Expose
    private Collection<BuildingAssignment> buildings;

    @Expose
    private Collection<Collection<String>> layers;

    private Collection<TownBlueprintLayer> buildingLayers;


    public void apply(World world, BlockPos startingPosition){

        int layerLevel = startingPosition.getY(); // we only increment this one as we move up a layer
        int startingX = startingPosition.getX();
        int startingZ = startingPosition.getZ();

        for(TownBlueprintLayer layer : getBuildingLayers()){

            BlockPos blockPos = new BlockPos(startingX, layerLevel, startingZ);
            layer.applyLayer(world, blockPos);

            layerLevel ++;
        }
    }

    public List<TownBuildingBlueprint> getBuildings(){
        List<TownBuildingBlueprint> buildings = new ArrayList<>();

        Collection<TownBlueprintLayer> layers = getBuildingLayers();
        for(TownBlueprintLayer layer : layers){
            buildings.addAll(layer.getBuildings());
        }

        return buildings;
    }

    private Collection<TownBlueprintLayer> getBuildingLayers(){
        //caching this so it doesn't have to happen every time you place a blueprint
        if(buildingLayers != null){
            return buildingLayers;
        }

        buildingLayers = new ArrayList<>();

        for(Collection<String> layer: layers){
            buildingLayers.add(new TownBlueprintLayer(buildings, layer));
        }

        return buildingLayers;
    }
}
