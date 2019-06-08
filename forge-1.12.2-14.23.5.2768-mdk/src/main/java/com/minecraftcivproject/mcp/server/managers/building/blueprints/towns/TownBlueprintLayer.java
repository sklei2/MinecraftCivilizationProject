package com.minecraftcivproject.mcp.server.managers.building.blueprints.towns;


import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.Blueprint;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import registry.BlueprintRegistry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class TownBlueprintLayer {

    private Map<String, Blueprint> buildingAssignmentMap = new HashMap<>();
    private Collection<TownBuildingBlueprint> buildingLayer = new ArrayList<>();
    private TownBuildingLayerParser townBuildingLayerParser = new TownBuildingLayerParser();

    private static final Logger LOG = Logger.getLogger("TownBlueprintLayer");

    public TownBlueprintLayer(Collection<BuildingAssignment> buildingAssignments, Collection<String> rawLayer){
        createBuildingAssignmentMap(buildingAssignments);
        createBuildingLayer(rawLayer);
    }

    public void applyLayer(World world, BlockPos topLeft){
        int startRow = topLeft.getX();
        int startCol = topLeft.getZ();
        int level = topLeft.getY();

        for(TownBuildingBlueprint building : buildingLayer){
            int row = startRow + building.getStartRow();
            int col = startCol + building.getStartCol();

            BlockPos buildingPosition = new BlockPos(row, level, col);

            building.getBuildingBlueprint().apply(world, buildingPosition);
        }
    }

    private void createBuildingLayer(Collection<String> rawLayer){
        char[][] rawCharacters = layerToArray(rawLayer);

        this.buildingLayer = townBuildingLayerParser.parse(rawCharacters, buildingAssignmentMap);
    }

    private char[][] layerToArray(Collection<String> rawLayer){
        int rows = rawLayer.size();
        int cols = getLayerMaxColumns(rawLayer);

        char[][] array = new char[rows][cols];

        int row = 0;
        for(String layer : rawLayer){
            int col = 0;
            char[] chars = layer.toCharArray();
            for(char c : chars){
                array[row][col] = c;
                ++ col;
            }
            ++row;
        }

        return array;
    }

    private int getLayerMaxColumns(Collection<String> layer){
        int max = 0;
        for(String s : layer){
            if(s.length() > max){
                max = s.length();
            }
        }

        return max;
    }

    private Blueprint getBlueprint(String s){
        return buildingAssignmentMap.get(s);
    }

    private void createBuildingAssignmentMap(Collection<BuildingAssignment> buildingAssignments){
        for(BuildingAssignment buildingAssignment : buildingAssignments){
            buildingAssignmentMap.put(buildingAssignment.getCharacter(), BlueprintRegistry.getBlueprint(buildingAssignment.getName()));
        }
    }
}
