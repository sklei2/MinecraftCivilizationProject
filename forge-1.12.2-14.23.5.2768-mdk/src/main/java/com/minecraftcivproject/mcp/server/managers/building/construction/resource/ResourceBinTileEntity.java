package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;

import java.util.UUID;


public class ResourceBinTileEntity extends TileEntityChest {

    private ResourceBinInventory resourceBinInventory;
    private UUID guid;

    public ResourceBinTileEntity(){
        
    }

    public ResourceBinTileEntity(UUID guid){
        this.guid = guid;
        this.resourceBinInventory = new ResourceBinInventory(this.guid);
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
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setString("TileEntityGuid", this.guid.toString());
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

    public UUID getGuid(){
        return guid;
    }
}
