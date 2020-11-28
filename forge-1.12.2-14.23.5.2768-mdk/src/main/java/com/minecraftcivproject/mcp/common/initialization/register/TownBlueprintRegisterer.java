package com.minecraftcivproject.mcp.common.initialization.register;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBlueprint;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBlueprintReader;
import registry.TownBlueprintRegistry;

public class TownBlueprintRegisterer {
    private static final String[] townBlueprintNames = new String[] {
            "test_town",
            "town_center_lvl1",
            "town"
    };

    private final TownBlueprintReader townBlueprintReader;

    public TownBlueprintRegisterer(){
        this.townBlueprintReader = new TownBlueprintReader();
    }

    public void register(){
        for(String townBlueprintName : townBlueprintNames){
            TownBlueprint townBlueprint = townBlueprintReader.readBlueprint(townBlueprintName);
            TownBlueprintRegistry.addTownBlueprint(townBlueprintName, townBlueprint);
        }
    }
}
