package registry;

import com.minecraftcivproject.mcp.server.managers.tribe.TribeManager;

import java.util.HashMap;
import java.util.Map;

public class TribeRegistry {
    private static Map<String, TribeManager> tribes = new HashMap<>();

    public static void addTribe(String tribeName, TribeManager tribeManager){
        tribes.put(tribeName, tribeManager);
    }

    public static TribeManager getTribe(String tribeName){
        return tribes.get(tribeName);
    }

}
