package com.minecraftcivproject.mcp.common.entity.task;

import com.minecraftcivproject.mcp.common.entity.task.core.OneTimeTask;
import com.minecraftcivproject.mcp.server.managers.building.construction.resource.ResourceBinTileEntity;
import com.minecraftcivproject.mcp.server.managers.resource.ItemGroup;
import com.minecraftcivproject.mcp.utils.Inventory;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.minecraftcivproject.mcp.utils.PositionUtils.distanceBetween;
import static java.lang.Thread.sleep;

public class ChestTransferTask extends OneTimeTask {

    private final World world;
    private final EntityLiving entity;
    private final ItemGroup itemsToTransfer;
    private final BlockPos chestLocation;
    private final Inventory inventory;

    private boolean wasSuccessful = false;

    public ChestTransferTask(World world, EntityLiving entity, Inventory inventory, ItemGroup itemsToTransfer, BlockPos chestLocation) {
        this.world = world;
        this.entity = entity;
        this.itemsToTransfer = itemsToTransfer;
        this.chestLocation = chestLocation;
        this.inventory = inventory;
    }

    @Override
    public void updateTask() {
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // if not close enough, fail
        if (distanceBetween(this.entity.getPosition(), this.chestLocation) > 2) {
            setDone();
            return;
        }

        TileEntity tileEntity = world.getTileEntity(chestLocation);

        // if not a resource bin, fail
        if (!(tileEntity instanceof ResourceBinTileEntity)) {
            setDone();
            return;
        }

        Inventory chestInventory = ((ResourceBinTileEntity) tileEntity).getInventory();

        // try to grab the items from the inventory
        ItemGroup itemsFailedToGet = inventory.removeItems(itemsToTransfer);
        if(!itemsFailedToGet.isEmpty()){
            inventory.add(itemsFailedToGet);
            setDone();
            return;
        }

        // if all the items couldn't be added, fail
        ItemGroup itemsThatCouldNotBeAdded = chestInventory.add(itemsToTransfer);
        if (!itemsThatCouldNotBeAdded.isEmpty()) {
            ItemGroup itemsThatWereAdded = itemsToTransfer.minus(itemsThatCouldNotBeAdded);
            chestInventory.removeItems(itemsThatWereAdded);
            setDone();
            return;
        }

        wasSuccessful = true;
        setDone();
    }

    @Override
    public boolean wasSuccessful() {
        return wasSuccessful;
    }

}
