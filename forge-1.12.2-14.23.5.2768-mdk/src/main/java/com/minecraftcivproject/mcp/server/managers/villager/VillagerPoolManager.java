package com.minecraftcivproject.mcp.server.managers.villager;

import com.minecraftcivproject.mcp.common.entity.LoyalVillager;
import net.minecraft.client.Minecraft;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VillagerPoolManager {

    public Map<String, LoyalVillager> villagers = new HashMap<>();

    public VillagerPoolManager(){
        //we will just have it spawn 1 villager for now
        addVillager(new LoyalVillager(Minecraft.getMinecraft().world));
    }

    public void addVillager(LoyalVillager loyalVillager){
        villagers.put(loyalVillager.getName(), loyalVillager);
    }

    public Optional<LoyalVillager> getAvailableVillager(){
        return villagers.values().stream().findFirst();
    }
}
