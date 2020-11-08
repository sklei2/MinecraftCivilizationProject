package com.minecraftcivproject.mcp.common.entity;

import com.minecraftcivproject.mcp.server.managers.resource.ItemGroup;
import com.minecraftcivproject.mcp.utils.InventoryUtils;
import net.minecraft.entity.EntityLiving;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.minecraftcivproject.mcp.utils.PositionUtils.distanceBetween;

/**
 * Takes an entity and has it retrieve certain items
 */
public class EntityItemFetcher {

    private final World world;
    private final EntityLiving entity;
    private final ItemSearcher itemSearcher;
    private final ItemGroup itemsToSearchFor;
    private final ItemGroup itemsFound;

    private final InventoryBasic inventory;

    private BlockPos destination = null;
    private Item itemSearchingFor = null;


    public EntityItemFetcher(World world, EntityLiving entity, InventoryBasic inventory, ItemGroup itemsToSearchFor){
        this.world = world;
        this.entity = entity;
        this.inventory = inventory;
        this.itemsToSearchFor = itemsToSearchFor;
        this.itemSearcher = new ItemSearcher(world);
        this.itemsFound = new ItemGroup();


    }

    public boolean isFetching(){
        return destination != null;
    }

    public void continueFetching(){

        // nothing left to do
        if(hasFetchedAll()){
            return;
        }

        // need a new objective
        if(!isFetching()){
            fetchNext();
            return;
        }

        // keep doin what you're doin
        if(distanceBetween(this.entity.getPosition(), this.destination) < 2){

            world.destroyBlock(destination,true);
            InventoryUtils.pickupItem(world, entity, inventory, itemSearchingFor);

            itemsFound.add(itemSearchingFor, 1);

            destination = null;
            itemSearchingFor = null;
        }
    }

    public void fetchNext(){
        ItemSearchResult itemSearchResult = itemSearcher.search(entity.getPosition(), 25, itemsToSearchFor.getAllItems());

        destination = itemSearchResult.getBlockPos();
        itemSearchingFor = itemSearchResult.getItem();

        this.entity.getNavigator().tryMoveToXYZ(destination.getX(), destination.getY(), destination.getZ(), 0.8D);
    }

    public boolean hasFetchedAll(){
        return itemsToSearchFor.minus(itemsFound).isEmpty();
    }
}
