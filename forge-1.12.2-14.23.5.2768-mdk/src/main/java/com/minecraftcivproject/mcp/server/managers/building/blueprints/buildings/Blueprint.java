package com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings;

import com.google.gson.annotations.Expose;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Blueprint {

    //public fields needed for deserialization.
    @Expose
    private String name;

    //notice how BuildingAssignment is defined for deserialization as well
    @Expose
    private Collection<BlockAssignment> blocks;

    @Expose
    private Collection<ResourceRequirement> resources;

    @Expose
    private Collection<Collection<String>> layers;

    private List<BlueprintLayer> blockLayers;
    private ResourceRequirements resourceRequirements;


    public Blueprint(String name, Collection<BlockAssignment> blocks, Collection<ResourceRequirement> resources, Collection<Collection<String>> layers) {
        this.name = name;
        this.blocks = blocks;
        this.resources = resources;
        this.layers = layers;
    }

    public void apply(World world, BlockPos startingPosition){

        int layerLevel = startingPosition.getY(); // we only increment this one as we move up a layer
        int startingX = startingPosition.getX();
        int startingZ = startingPosition.getZ();

        for(BlueprintLayer layer : getBlockLayers()){

            BlockPos blockPos = new BlockPos(startingX, layerLevel, startingZ);
            layer.applyLayer(world, blockPos);

            layerLevel ++;
        }
    }

    public int getBlueprintRows(){
        getBlockLayers();

        return blockLayers.get(0).getRows();
    }

    public int getBlueprintCols(){
        getBlockLayers();

        return blockLayers.get(0).getCols();
    }

    public ResourceRequirements getResourceRequirements(){
        if(this.resourceRequirements != null){
            return resourceRequirements;
        }

        this.resourceRequirements = new ResourceRequirements(resources);

        return resourceRequirements;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Blueprint other = (Blueprint) obj;

        return  this.getName().equals(other.getName()) &&
                this.getResourceRequirements().equals(other.getResourceRequirements()) &&
                this.getBlockLayers().equals(other.getBlockLayers());
    }

    public Collection<BlueprintLayer> getBlockLayers(){
        //caching this so it doesn't have to happen every time you place a blueprint
        if(blockLayers != null){
            return blockLayers;
        }

        blockLayers = new ArrayList<>();

        for(Collection<String> layer: layers){
            blockLayers.add(new BlueprintLayer(blocks, layer));
        }

        return blockLayers;
    }
}
