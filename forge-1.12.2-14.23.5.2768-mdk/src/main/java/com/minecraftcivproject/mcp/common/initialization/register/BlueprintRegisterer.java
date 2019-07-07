package com.minecraftcivproject.mcp.common.initialization.register;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.Blueprint;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.BlueprintReader;
import registry.BlueprintRegistry;

public class BlueprintRegisterer {

    private static final String[] blueprintNames = new String[] {
            "simple_house",
            "forge"
    };

    private final BlueprintReader blueprintReader;

    public BlueprintRegisterer(){
        this.blueprintReader = new BlueprintReader();
    }

    public void register(){
        for(String blueprintName : blueprintNames){
            Blueprint blueprint = blueprintReader.readBlueprint(blueprintName);
            BlueprintRegistry.addBlueprint(blueprintName, blueprint);
        }
    }
}
