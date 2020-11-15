package com.minecraftcivproject.mcp.common.entity.task;

import com.minecraftcivproject.mcp.common.entity.task.core.OneTimeTask;
import com.minecraftcivproject.mcp.server.managers.resource.ItemGroup;
import com.minecraftcivproject.mcp.utils.InventoryUtils;
import net.minecraft.entity.EntityLiving;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MineBlockTask extends OneTimeTask {

    private final World world;
    private final InventoryBasic inventory;
    private final EntityLiving entityLiving;
    private final BlockPos blockPosToMine;
    private ItemGroup result;

    public MineBlockTask(World world, InventoryBasic inventory, EntityLiving entityLiving, BlockPos blockPosToMine){
        this.world = world;
        this.inventory = inventory;
        this.entityLiving = entityLiving;
        this.blockPosToMine = blockPosToMine;
    }

    @Override
    public void updateTask(){
        Item item = Item.getItemFromBlock(world.getBlockState(blockPosToMine).getBlock());
        world.destroyBlock(blockPosToMine,true);
        InventoryUtils.pickupItem(world, entityLiving, inventory, item);

        result = new ItemGroup();
        result.add(item, 1);

        setDone();
    }

    public ItemGroup getResult(){
        return result;
    }
}
