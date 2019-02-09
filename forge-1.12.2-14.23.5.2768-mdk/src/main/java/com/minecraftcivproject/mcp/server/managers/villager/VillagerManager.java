package com.minecraftcivproject.mcp.server.managers.villager;

public class VillagerManager {

    private final VillagerPoolManager villagerPoolManager;
    private final VillagerTaskManager villagerTaskManager;

    public VillagerManager(){
        this(new VillagerPoolManager(), new VillagerTaskManager());
    }

    public VillagerManager(VillagerPoolManager villagerPoolManager, VillagerTaskManager villagerTaskManager){
        this.villagerPoolManager = villagerPoolManager;
        this.villagerTaskManager = villagerTaskManager;
    }
}
