package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import net.minecraft.block.BlockChest;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class ResourceBinBlock extends BlockChest {

    public static final String NAME = "resource_bin";

    private ResourceBinTileEntity tileEntityChest;
    private Runnable updatedCallback;

    //just used for registration
    public ResourceBinBlock(){
        super(Type.BASIC);
        setRegistryName(NAME);
    }

    public ResourceBinBlock(Runnable updatedCallback){
        this();
        this.updatedCallback = updatedCallback;
        this.tileEntityChest = new ResourceBinTileEntity(updatedCallback);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
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
}
