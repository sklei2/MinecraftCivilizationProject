package registry;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.Blueprint;

import java.util.HashMap;
import java.util.Map;

public class BlueprintRegistry {
    private static Map<String, Blueprint> blueprints = new HashMap<>();

    public static void addBlueprint(String blueprintName, Blueprint blueprint){
        blueprints.put(blueprintName, blueprint);
    }

    public static Blueprint getBlueprint(String blueprintName){
        return blueprints.get(blueprintName);
    }
}
