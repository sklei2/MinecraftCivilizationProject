package registry;

import com.minecraftcivproject.mcp.server.managers.tribe.TribeManager;
import ui.tribe.TribeUi;

import java.util.*;

public class TribeRegistry {
    private static Map<String, TribeManager> tribes = new HashMap<>();
    private static Map<String, TribeUi> tribeUis = new HashMap<>();
    private static List<Runnable> observers = new ArrayList<>();

    public static void addTribe(String tribeName, TribeManager tribeManager){
        tribes.put(tribeName, tribeManager);
        tribeUis.put(tribeName, new TribeUi(tribeManager));

        notifyObservers();
    }

    public static TribeManager getTribe(String tribeName){
        return tribes.get(tribeName);
    }

    public static TribeUi getTribeUi(String tribeName){
        return tribeUis.get(tribeName);
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
