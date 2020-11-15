package com.minecraftcivproject.mcp.server.managers.villager;

import com.minecraftcivproject.mcp.common.entity.LoyalVillager;
import com.minecraftcivproject.mcp.common.entity.task.BuildTask;
import com.minecraftcivproject.mcp.common.queueable.Order;
import com.minecraftcivproject.mcp.server.managers.TickableManager;
import com.minecraftcivproject.mcp.server.managers.building.construction.ConstructionProject;
import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;
import com.minecraftcivproject.mcp.server.managers.resource.ItemRequest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;

public class VillagerManager implements TickableManager {

    private final VillagerPoolManager villagerPoolManager;
    private final VillagerTaskManager villagerTaskManager;
    private final QueueManager queueManager;
    private final World world;
    private final BlockPos townLocation;

    public VillagerManager(QueueManager queueManager, World world, BlockPos townLocation){
        this(new VillagerPoolManager(world, townLocation), new VillagerTaskManager(), world, townLocation, queueManager);
    }

    public VillagerManager(VillagerPoolManager villagerPoolManager, VillagerTaskManager villagerTaskManager, World world, BlockPos townLocation, QueueManager queueManager){
        this.villagerPoolManager = villagerPoolManager;
        this.villagerTaskManager = villagerTaskManager;
        this.queueManager = queueManager;
        this.world = world;
        this.townLocation = townLocation;
    }

    public void addNewVillager(LoyalVillager loyalVillager){
        villagerPoolManager.addVillager(loyalVillager);
    }

    @Override
    public void onTick() {
        for(ConstructionProject constructionProject : queueManager.getConstructionProjectQueue().getAllItems()){
            Optional<LoyalVillager> nextAvailableLoyalVillager = villagerPoolManager.getAvailableVillager();
            if(nextAvailableLoyalVillager.isPresent()){
                LoyalVillager loyalVillager = nextAvailableLoyalVillager.get();

                Order order = new Order(new ItemRequest(constructionProject.getDropoffLocation(), constructionProject.getResourceRequirements()));

                loyalVillager.assignTask(new BuildTask(world, loyalVillager, order));
            }

        }
    }
}
