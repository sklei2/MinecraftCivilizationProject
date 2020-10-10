package registry;

import com.minecraftcivproject.mcp.server.managers.building.construction.resource.ResourceBinInventory;

import java.util.HashMap;
import java.util.Map;

/*
Stores resource bin inventories when they aren't loaded
 */
public class ResourceBinInventoryRegistry {

    private static Map<String, ResourceBinInventory> resourceBinInventories = new HashMap<>();

    public static void add(String id, ResourceBinInventory resourceBinInventory){
        resourceBinInventories.put(id, resourceBinInventory);
    }

    public static ResourceBinInventory get(String id){
        return resourceBinInventories.get(id);
    }
}
