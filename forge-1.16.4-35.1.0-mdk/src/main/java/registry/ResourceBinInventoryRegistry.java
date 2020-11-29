package registry;

import com.minecraftcivproject.mcp.server.managers.building.construction.resource.ResourceBinInventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Stores resource bin inventories when they aren't loaded
 */
public class ResourceBinInventoryRegistry {

    private static Map<String, ResourceBinInventory> resourceBinInventories = new HashMap<>();
    private static Map<String, List<Runnable>> resourceBinSubscribers = new HashMap<>();

    public static void add(String id, ResourceBinInventory resourceBinInventory){
        System.out.println("add " + id + " " + resourceBinInventory);
        resourceBinInventories.put(id, resourceBinInventory);
    }

    public static ResourceBinInventory get(String id){
        System.out.println("get " + id);
        return resourceBinInventories.get(id);
    }

    public static void subscribe(String id, Runnable runnable){
        System.out.println("subscribe " + id + " " + runnable);

        List<Runnable> runnables = resourceBinSubscribers.getOrDefault(id, new ArrayList<>());
        runnables.add(runnable);
        resourceBinSubscribers.put(id, runnables);
    }

    public static void trigger(String id){
        if(resourceBinSubscribers.get(id).isEmpty()){
            System.out.println("nothing to trigger for " + id);
            return;
        }

        System.out.println("trigger " + id);
        for (Runnable r : resourceBinSubscribers.get(id)){
            r.run();
        }
    }
}
