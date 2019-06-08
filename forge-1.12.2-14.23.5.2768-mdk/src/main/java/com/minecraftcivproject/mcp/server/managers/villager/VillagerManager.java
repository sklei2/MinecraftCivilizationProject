package com.minecraftcivproject.mcp.server.managers.villager;

import com.minecraftcivproject.mcp.common.npc.LoyalVillager;

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

    public void addNewVillager(LoyalVillager loyalVillager){
        villagerPoolManager.addVillager(loyalVillager);
    }
}
