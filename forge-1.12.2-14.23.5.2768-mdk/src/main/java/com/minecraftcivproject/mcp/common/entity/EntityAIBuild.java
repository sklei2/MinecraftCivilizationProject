package com.minecraftcivproject.mcp.common.entity;

import com.minecraftcivproject.mcp.common.queueable.Order;
import com.minecraftcivproject.mcp.server.managers.resource.ItemGroup;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.logging.Logger;


public class EntityAIBuild extends EntityAIBase {

    private static Logger logger = Logger.getLogger("EntityAIBuild");
    public World world;  // This is a pointer b/c it's not a primitive type
    private LoyalVillager entity;
    private Order order;
    public BlockPos entityPosition;  // BlockPos pos; pos.getX() pos.getY() pos.getZ() returns that x,y,z position of a block
    public BlockPos pos;  // This is the position of the desired block within the search area
    public BlockPos newPos;  // This is the new position the entity should move to
    public LVInventory inventory;

    private EntityItemFetcher entityItemFetcher;


    public EntityAIBuild(World worldIn, LoyalVillager entityIn, Order orderIn, boolean doTheThing) {
        this.world = worldIn;  // Assigns the field/attribute of "world" to the object, aka a new EntityAIBuild almost like a class specific global variable (.field)
        this.entity = entityIn;
        this.order = orderIn;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        logger.info("should execute called");

            this.inventory = this.entity.getInventory();

            ItemGroup currentInventory = this.inventory.getInventoryItems();
            ItemGroup requestedItems = this.order.getRemainingRequiredItems();

            ItemGroup remainingRequiredResources = requestedItems.minus(currentInventory);

            // we have all the items to fill the order
            if (remainingRequiredResources.isEmpty()) {
                this.order.fill(requestedItems);
                //TODO: Figure out how to remove those from the inventory
            }

            return !this.order.isOrderFullfilled();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        logger.info("start executing called");

        ItemGroup remainingItems = order.getRemainingRequiredItems();  // This check should take into account the inventory contents prior to the search

        // nothing to do
        if (remainingItems.isEmpty()) {
            return;
        }

        this.entityItemFetcher = new EntityItemFetcher(this.world, this.entity, this.inventory, remainingItems);
        this.entityItemFetcher.fetchNext();
    }


    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask() {
        logger.info("updateTask called");

        this.entityItemFetcher.continueFetching();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean shouldContinueExecuting() {
        logger.info("shouldContinueExecuting called");

        if (entityItemFetcher.hasFetchedAll()) {
            order.fill(order.getRemainingRequiredItems());
        }

        return order.isOrderFullfilled();
    }

}
