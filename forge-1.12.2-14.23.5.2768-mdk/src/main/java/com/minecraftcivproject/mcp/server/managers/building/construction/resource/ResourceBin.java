package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.ResourceRequirements;
import net.minecraft.block.BlockChest;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class ResourceBin extends BlockChest {

    private ResourceRequirements resourceRequirements;
    private ResourceBinTileEntity tileEntityChest;
    private Runnable fullCallback;

    public ResourceBin(ResourceRequirements resourceRequirements, Runnable fullCallback){
        super(Type.BASIC);

        this.resourceRequirements = resourceRequirements;

        this.fullCallback = fullCallback;
    }

    public ResourceRequirements getResourceRequirements() {
        return resourceRequirements;
    }

    public boolean isFull(){
        for(String resource : resourceRequirements.getRequirements()){
            int current = get(resource);
            int required = resourceRequirements.getRequirement(resource);

            if(current < required){
                return false;
            }
        }

        return true;
    }

    public void onUpdate(){
        if(isFull()){
            fullCallback.run();
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        this.tileEntityChest = new ResourceBinTileEntity(this::onUpdate);
        return this.tileEntityChest;
    }

    public void add(Item i, int count){
        this.tileEntityChest.add(i, count);
    }

    public void add(String name, int count){
        add(Item.getByNameOrId(name), count);
    }

    public void remove(Item i, int count){
        this.tileEntityChest.remove(i, count);
    }

    public int get(Item i){
        return this.tileEntityChest.getCount(i);
    }

    public int get(String name){
        return get(Item.getByNameOrId(name));
    }
}
