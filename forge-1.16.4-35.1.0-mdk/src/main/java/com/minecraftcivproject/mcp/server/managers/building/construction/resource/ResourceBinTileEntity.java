package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import com.minecraftcivproject.mcp.server.managers.resource.ItemGroup;
import com.minecraftcivproject.mcp.utils.Inventory;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.command.arguments.NBTCompoundTagArgument;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.tileentity.ChestTileEntity;
import registry.ResourceBinInventoryRegistry;


public class ResourceBinTileEntity extends ChestTileEntity {

    private ResourceBinInventory resourceBinInventory;
    private String id;

    public ResourceBinTileEntity(){

    }

    public ResourceBinTileEntity(String id, ResourceBinInventory resourceBinInventory){
        this.id = id;
        this.resourceBinInventory = resourceBinInventory;
        ResourceBinInventoryRegistry.add(id, this.resourceBinInventory);
    }

    public String getId() {
        return this.id;
    }

    @Override
    public Container createContainer(Inventory playerInventory, ClientPlayerEntity playerIn)
    {
        this.fillWithLoot(playerIn);
        return new ContainerChest(playerInventory,resourceBinInventory, playerIn);
    }

    @Override
    public void readFromNBT(NBTCompoundTagArgument compound)
    {
        super.readFromNBT(compound);
        this.id = compound.getString("TileEntityGuid");

        System.out.println("Reading " + this.id);

        this.resourceBinInventory = ResourceBinInventoryRegistry.get(this.id);

        System.out.println(this.resourceBinInventory);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setString("TileEntityGuid", this.id);

        ResourceBinInventoryRegistry.add(id, this.resourceBinInventory);

        System.out.println("Writing " + id);

        return compound;
    }

    public ItemGroup add(ItemGroup itemGroup){
        ItemGroup itemsThatCouldNotBeAdded = new ItemGroup();

        for(Item item : itemGroup.getAllItems()){
            int leftover = add(item, itemGroup.getNumberOfItem(item));
            if(leftover > 0){
                itemsThatCouldNotBeAdded.add(item, leftover);
            }
        }

        return itemsThatCouldNotBeAdded;
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

    public Inventory getInventory(){
        return this.resourceBinInventory;
    }
}
