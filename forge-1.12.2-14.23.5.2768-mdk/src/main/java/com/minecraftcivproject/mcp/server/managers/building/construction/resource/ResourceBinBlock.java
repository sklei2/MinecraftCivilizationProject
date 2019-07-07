package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import net.minecraft.block.BlockChest;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.UUID;


public class ResourceBinBlock extends BlockChest {

    private UUID guid;
    private ResourceBinTileEntity tileEntityChest;

    public ResourceBinBlock(UUID guid){
        super(Type.BASIC);
        this.guid = guid;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        this.tileEntityChest = new ResourceBinTileEntity(guid);
        return this.tileEntityChest;
    }


    public int add(Item i, int count){
        return this.tileEntityChest.add(i, count);
    }

    public int add(String name, int count){
        return add(Item.getByNameOrId(name), count);
    }

    public int remove(Item i, int count){
        return this.tileEntityChest.remove(i, count);
    }

    public int get(Item i){
        return this.tileEntityChest.getCount(i);
    }

    public int get(String name){
        return get(Item.getByNameOrId(name));
    }

    public UUID getGuid(){
        return guid;
    }
}
