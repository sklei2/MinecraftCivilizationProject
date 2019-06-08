package com.minecraftcivproject.mcp.server.managers.building.blueprints.towns;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.Blueprint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TownBuildingLayerParser {

    public Collection<TownBuildingBlueprint> parse(char[][] layer, Map<String, Blueprint> buildingAssignmentMap){
        List<TownBuildingBlueprint> townBuildings = new ArrayList<>();

        BuildingChar[][] buildingChars = createBuildingCharArray(layer);

        int row = 0;
        for(BuildingChar[] charRow : buildingChars){
            int col = 0;
            for(BuildingChar buildingChar : charRow){
                if(!(buildingChar.isParsed() || buildingChar.character == ' ')) {
                    Blueprint building = buildingAssignmentMap.get(buildingChar.getCharacter() + "");
                    markBuilding(buildingChars, row, col, building);
                    townBuildings.add(new TownBuildingBlueprint(building, row, col));
                }
                ++col;
            }
            ++row;
        }

        return townBuildings;
    }

    private void markBuilding(BuildingChar[][] buildingChars, int startRow, int startCol, Blueprint building){
        int numRowsToMark = building.getBlueprintRows();
        int numColsToMark = building.getBlueprintCols();

        for(int numRowsMarked = 0; numRowsMarked < numRowsToMark; ++numRowsMarked){
            for(int numColsMarked = 0; numColsMarked < numColsToMark; ++numColsMarked){
                buildingChars[startRow + numRowsMarked][startCol + numColsMarked].setParsed();
            }
        }
    }

    private BuildingChar[][] createBuildingCharArray(char[][] layer){
        BuildingChar[][] buildingChars = new BuildingChar[layer.length][layer[0].length];

        int row = 0;
        for(char[] rowChars : layer){
            int col = 0;
            for(char colChar : rowChars){
                buildingChars[row][col] = new BuildingChar(colChar);
                ++col;
            }
            ++row;
        }

        return buildingChars;
    }

    private class BuildingChar {
        private char character;
        private boolean parsed = false;

        public BuildingChar(char c){
            character = c;
        }

        public char getCharacter() {
            return character;
        }

        public boolean isParsed() {
            return parsed;
        }

        public void setParsed() {
            parsed = true;
        }
    }

}
