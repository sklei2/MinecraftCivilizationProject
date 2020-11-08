package com.minecraftcivproject.mcp.server.managers.villager;

import com.minecraftcivproject.mcp.common.entity.LoyalVillager;
import com.minecraftcivproject.mcp.server.managers.TickableManager;
import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;

public class VillagerManager implements TickableManager {

    private final VillagerPoolManager villagerPoolManager;
    private final VillagerTaskManager villagerTaskManager;
    private final QueueManager queueManager;

    public VillagerManager(QueueManager queueManager){
        this(new VillagerPoolManager(), new VillagerTaskManager(), queueManager);
    }

    public VillagerManager(VillagerPoolManager villagerPoolManager, VillagerTaskManager villagerTaskManager, QueueManager queueManager){
        this.villagerPoolManager = villagerPoolManager;
        this.villagerTaskManager = villagerTaskManager;
        this.queueManager = queueManager;
    }

    public void addNewVillager(LoyalVillager loyalVillager){
        villagerPoolManager.addVillager(loyalVillager);
    }

    @Override
    public void onTick() {

    }
}
