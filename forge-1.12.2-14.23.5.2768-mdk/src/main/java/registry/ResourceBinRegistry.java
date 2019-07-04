package registry;

import com.minecraftcivproject.mcp.server.managers.building.construction.resource.ResourceBin;

import java.util.HashMap;
import java.util.Map;

/*
Stores resource bins when they aren't loaded
 */
public class ResourceBinRegistry {

    private static Map<String, ResourceBin> resourceBins = new HashMap<>();

    public static void add(ResourceBin resourceBin){
        resourceBins.put(resourceBin.getUniqueIdentifier(), resourceBin);
    }

    public static void notify(String id){
        resourceBins.get(id).onUpdate();
    }
}
