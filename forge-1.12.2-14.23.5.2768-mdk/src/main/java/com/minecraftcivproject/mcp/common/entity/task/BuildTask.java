package com.minecraftcivproject.mcp.common.entity.task;

import com.minecraftcivproject.mcp.common.entity.LoyalVillager;
import com.minecraftcivproject.mcp.common.entity.task.core.MultiStepTask;
import com.minecraftcivproject.mcp.common.queueable.Order;
import com.minecraftcivproject.mcp.server.managers.resource.ItemGroup;
import net.minecraft.world.World;


public class BuildTask extends MultiStepTask {

    public BuildTask(World world, LoyalVillager entity, Order order) {
        ItemGroup remainingItems = order.getRemainingRequiredItems();

        addSubtask(new FetchItemTask(world, entity, entity.getInventory(), remainingItems));
        addSubtask(new MoveToBlockTask(entity, order.getDropoffLocation()));
    }
}
