package com.minecraftcivproject.mcp.server.managers.building.blueprints;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Blueprint {

    //public fields needed for deserialization.
    public String name;

    //notice how BlockAssignment is defined for deserialization as well
    public Collection<BlockAssignment> blocks;
    public Collection<Collection<String>> rawLayers;

    private Collection<BlueprintLayer> layers;


    public Blueprint(){
        this.layers = getLayers();
    }

    public Collection<BlueprintLayer> getLayers(){
        List<BlueprintLayer> blueprintLayers = new ArrayList<>();

        for(Collection<String> layer: rawLayers){
            blueprintLayers.add(new BlueprintLayer(blocks, layer));
        }

        return blueprintLayers;
    }

    public void apply(World world, BlockPos startingPosition){

        int layerLevel = startingPosition.getY(); // we only increment this one as we move up a layer
        int startingX = startingPosition.getX();
        int startingZ = startingPosition.getZ();

        for(BlueprintLayer layer : layers){

            BlockPos blockPos = new BlockPos(startingX, layerLevel, startingZ);
            layer.applyLayer(world, blockPos);

            layerLevel ++;
        }
    }
}
