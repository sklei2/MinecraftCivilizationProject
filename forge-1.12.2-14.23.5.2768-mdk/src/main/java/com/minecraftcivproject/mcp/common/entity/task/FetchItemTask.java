package com.minecraftcivproject.mcp.common.entity.task;

import com.minecraftcivproject.mcp.common.entity.search.ItemSearchResult;
import com.minecraftcivproject.mcp.common.entity.search.ItemSearcher;
import com.minecraftcivproject.mcp.common.entity.task.core.MultiStepTask;
import com.minecraftcivproject.mcp.common.entity.task.core.Task;
import com.minecraftcivproject.mcp.server.managers.resource.ItemGroup;
import com.minecraftcivproject.mcp.utils.TaskUtils;
import net.minecraft.entity.EntityLiving;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.world.World;

/**
 * Takes an entity and has it retrieve certain items
 */
public class FetchItemTask extends MultiStepTask {

    private final World world;
    private final EntityLiving entity;
    private final ItemSearcher itemSearcher;
    private final ItemGroup itemsToSearchFor;
    private ItemGroup itemsFound;

    private final InventoryBasic inventory;


    public FetchItemTask(World world, EntityLiving entity, InventoryBasic inventory, ItemGroup itemsToSearchFor){
        this.world = world;
        this.entity = entity;
        this.inventory = inventory;
        this.itemsToSearchFor = itemsToSearchFor;
        this.itemSearcher = new ItemSearcher(world);
        this.itemsFound = new ItemGroup();
    }

    @Override
    public void startExecuting(){
        if(!hasFetchedAll()){
            beginToFetchNext();
        }

        super.startExecuting();
    }

    @Override
    protected void onSubtaskCompleted(Task completedTask) {
        if(completedTask instanceof MineBlockTask){
            MineBlockTask mineBlockTask = (MineBlockTask) completedTask;
            itemsFound = itemsFound.plus(mineBlockTask.getResult());
        }
    }

    @Override
    protected void onAllSubtasksCompleted() {
        TaskUtils.runAboutOnceOutOfXTimes(this::beginToFetchNext, 20);
    }

    @Override
    public boolean isDone() {
        return hasFetchedAll();
    }

    private void beginToFetchNext(){
        ItemSearchResult itemSearchResult = itemSearcher.search(entity.getPosition(), 25, itemsToSearchFor.getAllItems());

        // if we find a block
        if(itemSearchResult.getBlockPos() != null){
            addSubtask(new MoveToBlockTask(entity, itemSearchResult.getBlockPos()));
            addSubtask(new MineBlockTask(world, inventory, entity, itemSearchResult.getBlockPos()));
        }
    }

    private boolean hasFetchedAll(){
        ItemGroup remaining =  itemsToSearchFor.minus(itemsFound);
        return remaining.isEmpty();
    }
}
