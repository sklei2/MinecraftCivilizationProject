package com.minecraftcivproject.mcp.common.entity.task;

import com.minecraftcivproject.mcp.common.entity.LoyalVillager;
import com.minecraftcivproject.mcp.common.entity.task.core.Task;
import com.minecraftcivproject.mcp.common.entity.task.core.TaskQueue;
import com.minecraftcivproject.mcp.common.queueable.Order;
import com.minecraftcivproject.mcp.server.managers.resource.ItemGroup;
import net.minecraft.world.World;


public class BuildTask extends Task {

    public BuildTask(World world, LoyalVillager entity, Order order) {
        ItemGroup remainingItems = order.getRemainingRequiredItems();

        System.out.println(remainingItems);

        addSubtask(
                new TaskQueue()
                    .then(new FetchItemTask(world, entity, entity.getInventory(), remainingItems))
                    .then(new MoveToBlockTask(entity, order.getDropoffLocation()))
                    .then(new ChestTransferTask(world, entity, entity.getInventory(), remainingItems, order.getDropoffLocation()))
        );
    }
}
