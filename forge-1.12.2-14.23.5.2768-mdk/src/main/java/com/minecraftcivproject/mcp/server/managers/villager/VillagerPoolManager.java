package com.minecraftcivproject.mcp.server.managers.villager;

import com.minecraftcivproject.mcp.common.entity.LoyalVillager;
import com.minecraftcivproject.mcp.utils.SpawningUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VillagerPoolManager {

    private final Map<String, LoyalVillager> villagers = new HashMap<>();
    private final BlockPos villagerSpawnPoint;
    private final World world;

    public VillagerPoolManager(World world, BlockPos townLocation) {
        this.world = world;
        villagerSpawnPoint = townLocation.add(1,2,1);  // Spawns LV on top on nexus
        addVillager(new LoyalVillager(world));
    }

    public void addVillager(LoyalVillager loyalVillager) {
        villagers.put(loyalVillager.getName(), loyalVillager);
        SpawningUtils.spawn(loyalVillager, world, villagerSpawnPoint);
        System.out.println("LV has spawned!!!");
    }

    public Optional<LoyalVillager> getAvailableVillager() {

        Optional<LoyalVillager> loyalVillager = villagers.values().stream().findFirst();
        loyalVillager.ifPresent(lv -> villagers.remove(lv.getName()));

        return loyalVillager;
    }
}
