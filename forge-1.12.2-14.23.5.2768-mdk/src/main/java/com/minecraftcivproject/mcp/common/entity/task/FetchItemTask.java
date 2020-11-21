package com.minecraftcivproject.mcp.common.entity.task;

import com.minecraftcivproject.mcp.common.entity.search.ItemSearchResult;
import com.minecraftcivproject.mcp.common.entity.search.ItemSearcher;
import com.minecraftcivproject.mcp.common.entity.task.core.Task;
import com.minecraftcivproject.mcp.common.entity.task.core.TaskQueue;
import com.minecraftcivproject.mcp.server.managers.resource.ItemGroup;
import com.minecraftcivproject.mcp.utils.TaskUtils;
import net.minecraft.entity.EntityLiving;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.world.World;

/**
 * Takes an entity and has it retrieve certain items
 */
public class FetchItemTask extends Task {

    private final World world;
    private final EntityLiving entity;
    private final ItemSearcher itemSearcher;
    private final ItemGroup itemsToFetch;
    private ItemGroup itemsFound;

    private final InventoryBasic inventory;

    private MineBlockTask mineBlockTask;


    public FetchItemTask(World world, EntityLiving entity, InventoryBasic inventory, ItemGroup itemsToFetch) {
        this.world = world;
        this.entity = entity;
        this.inventory = inventory;
        this.itemsToFetch = itemsToFetch;
        this.itemSearcher = new ItemSearcher(world);
        this.itemsFound = new ItemGroup();
    }

    @Override
    protected void start() {
        beginToFetchNext();
    }

    @Override
    protected void onTick() {

        // if we found resources, add them
        if(mineBlockTask != null && mineBlockTask.isDone()){
            itemsFound = itemsFound.plus(mineBlockTask.getResult());
            System.out.println("CurrentItems: " + itemsFound);
            System.out.println("Wanted Items: " + itemsToFetch);
            mineBlockTask = null;
        }

        // we need to keep looking
        if (!isRunningSubtasks()) {
            TaskUtils.runAboutOnceOutOfXTimes(this::beginToFetchNext, 1);
        }
    }

    @Override
    public boolean isDone() {
        return hasFetchedAll();
    }

    private void beginToFetchNext() {
        ItemGroup itemsToSearchFor = itemsToFetch.minus(itemsFound);

        ItemSearchResult itemSearchResult = itemSearcher.search(entity.getPosition(), 25, itemsToSearchFor.getAllNonZeroItems());

        // if we find a block
        if (itemSearchResult.getBlockPos() != null) {

            mineBlockTask = new MineBlockTask(world, inventory, entity, itemSearchResult.getBlockPos());

            addSubtask(
                    new TaskQueue()
                            .then(new MoveToBlockTask(entity, itemSearchResult.getBlockPos()))
                            .then(mineBlockTask)
            );
        }
    }

    private boolean hasFetchedAll() {
        ItemGroup remaining = itemsToFetch.minus(itemsFound);
        return remaining.isEmpty();
    }
}
