package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import com.minecraftcivproject.mcp.common.initialization.register.BlockRegisterer;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.ResourceRequirements;
import com.minecraftcivproject.mcp.utils.BlockUtils;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import registry.BlockRegistry;
import registry.ResourceBinInventoryRegistry;

import java.util.Observable;

public class ResourceBin extends Observable {

    private ResourceRequirements resourceRequirements;
//    private static ResourceBinBlock resourceBinBlock;
    private ResourceBinBlock resourceBinBlock;
    private Runnable fullCallback;
    private final String id;

    public ResourceBin(ResourceBinTileEntity resourceBinTileEntity, ResourceRequirements resourceRequirements, BlockPos pos, Runnable runnable){
        this.resourceRequirements = resourceRequirements;

//        this.resourceBinBlock = new ResourceBinBlock();
          // Does this mean it's the same resource bin block for every new resource bin created?? If so, we may have to dynamically "register" every single resource bin block created...
//        ResourceBinBlock resourceBinBlock = new ResourceBinBlock();
//        BlockRegisterer.registerNewBlock(resourceBinBlock, resourceBinBlock.getId());
//        //this.resourceBinBlock = (ResourceBinBlock)BlockRegistry.getBlock(resourceBinBlock.getId());  // Idk how to get around casting here...
//        this.resourceBinBlock = (ResourceBinBlock)BlockRegistry.getRegisteredBlock(resourceBinBlock.getId());

        this.fullCallback = runnable;

        // because this can be created both by placement and automatedly
        this.id = resourceBinTileEntity.getId();  // Should be the same as above...
        ResourceBinInventoryRegistry.subscribe(id, this::onUpdate);
    }

    public static ResourceBin placeResourceBin(World world, ResourceRequirements resourceRequirements, BlockPos pos, Runnable runnable) {
        BlockUtils.placeBlock(world, pos, BlockRegisterer.RegistrationHandler.RESOURCE_BIN_BLOCK);
        ResourceBinTileEntity resourceBinTileEntity = (ResourceBinTileEntity)world.getTileEntity(pos);
        return new ResourceBin(resourceBinTileEntity, resourceRequirements, pos, runnable);
    }

    public ResourceRequirements getResourceRequirements() {
        return resourceRequirements;
    }


    public boolean isFull(){
        for(String resource : resourceRequirements.getAllResourceNames()){
            int current = getInventory().getCount(Item.getByNameOrId(resource));
            int required = resourceRequirements.getRequirement(resource);

            if(current < required){
                return false;
            }
        }

        return true;
    }

    public int add(Item i, int count){
        return getInventory().add(i, count);
    }

    public int add(String name, int count){
        return add(Item.getByNameOrId(name), count);
    }

    public int remove(Item i, int count){
        return getInventory().remove(i, count);
    }

    public int get(Item i){
        return getInventory().getCount(i);
    }

    public int get(String name){
        return get(Item.getByNameOrId(name));
    }

    public ResourceBinBlock getResourceBinBlock(){
        return resourceBinBlock;
    }

    public void onUpdate(){
        if(isFull()){
            this.fullCallback.run();
        }

        System.out.println("Resource bin " + id + " has been updated " + countObservers());
        setChanged();
        notifyObservers();
    }

    private ResourceBinInventory getInventory(){
        return ResourceBinInventoryRegistry.get(id);
    }
}
