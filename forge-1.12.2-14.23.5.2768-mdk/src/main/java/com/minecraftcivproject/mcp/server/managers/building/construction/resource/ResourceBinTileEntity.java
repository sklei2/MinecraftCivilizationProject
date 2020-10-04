package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import registry.ResourceBinInventoryRegistry;

import java.util.UUID;


public class ResourceBinTileEntity extends TileEntityChest {

    private ResourceBinInventory resourceBinInventory;
    private UUID guid;

    public ResourceBinTileEntity(){

    }

    public ResourceBinTileEntity(Runnable updatedCallback){
        this.guid = UUID.randomUUID();
        this.resourceBinInventory = new ResourceBinInventory(updatedCallback);
        ResourceBinInventoryRegistry.add(guid.toString(), this.resourceBinInventory);
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        this.fillWithLoot(playerIn);
        return new ContainerChest(playerInventory,resourceBinInventory, playerIn);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.guid = UUID.fromString(compound.getString("TileEntityGuid"));

        System.out.println("Reading " + this.guid.toString());

        this.resourceBinInventory = ResourceBinInventoryRegistry.get(this.guid.toString());

        System.out.println(this.resourceBinInventory);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setString("TileEntityGuid", this.guid.toString());

        System.out.println("Writing " + this.guid.toString());

        return compound;
    }


    public int add(Item item, int count){
       return this.resourceBinInventory.add(item, count);
    }

    public int remove(Item item, int count){
        return this.resourceBinInventory.remove(item, count);
    }

    public int getCount(Item item){
        return this.resourceBinInventory.getCount(item);
    }
}
