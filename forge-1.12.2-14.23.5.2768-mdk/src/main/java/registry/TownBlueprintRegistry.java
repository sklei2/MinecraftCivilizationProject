package registry;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBlueprint;

import java.util.HashMap;
import java.util.Map;

public class TownBlueprintRegistry {
    private static Map<String, TownBlueprint> townBlueprints = new HashMap<>();

    public static void addTownBlueprint(String townBlueprintName, TownBlueprint townBlueprint){
        townBlueprints.put(townBlueprintName, townBlueprint);
    }

    public static TownBlueprint getTownBlueprint(String townBlueprintName){
        return townBlueprints.get(townBlueprintName);
    }
}
