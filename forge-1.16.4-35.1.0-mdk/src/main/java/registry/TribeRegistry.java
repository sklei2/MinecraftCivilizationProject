package registry;

import com.minecraftcivproject.mcp.server.managers.tribe.Tribe;
import com.minecraftcivproject.mcp.server.managers.tribe.TribeManager;
import net.minecraft.world.World;
import ui.tribe.general.TribeUi;

import java.util.*;

public class TribeRegistry {
    private static Map<String, Tribe> tribes = new HashMap<>();
    private static List<Runnable> observers = new ArrayList<>();

    public static void addTribe(String tribeName, Tribe tribe){
        tribes.put(tribeName, tribe);

        notifyObservers();
    }

    public static Tribe getTribe(String tribeName){
        return tribes.get(tribeName);
    }

    public static TribeUi getTribeUi(String tribeName){
        return tribes.get(tribeName).getTribeUi();
    }

    public static TribeManager getTribeManager(String tribeName){
        return tribes.get(tribeName).getTribeManager();
    }

    public static World getWorld(String tribeName){
        return tribes.get(tribeName).getWorld();
    }

    public static Collection<String> getAllTribes(){
        return tribes.keySet();
    }

    public static void addObserver(Runnable o){
        observers.add(o);
    }

    public static void notifyObservers(){
        observers.forEach(Runnable::run);
    }

}
