package com.minecraftcivproject.mcp.common.entity;

import com.minecraftcivproject.mcp.common.entity.task.OneTimeTask;
import com.minecraftcivproject.mcp.common.queueable.Order;
import com.minecraftcivproject.mcp.server.managers.resource.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.logging.Logger;


public class EntityAIBuild extends OneTimeTask {

    private static Logger logger = Logger.getLogger("EntityAIBuild");
    public World world;  // This is a pointer b/c it's not a primitive type
    private LoyalVillager entity;
    private Order order;
    public BlockPos entityPosition;  // BlockPos pos; pos.getX() pos.getY() pos.getZ() returns that x,y,z position of a block
    public BlockPos pos;  // This is the position of the desired block within the search area
    public BlockPos newPos;  // This is the new position the entity should move to
    public LVInventory inventory;

    private EntityItemFetcher entityItemFetcher;


    public EntityAIBuild(int priority, World worldIn, LoyalVillager entityIn, Order orderIn, boolean doTheThing) {
        super(priority);  // THIS SEEMS TO BE CALLED FIRST
        this.world = worldIn;  // Assigns the field/attribute of "world" to the object, aka a new EntityAIBuild almost like a class specific global variable (.field)
        this.entity = entityIn;  // It seems like if a field/attribute like this is created, it can be referenced by any method inside of this class without having it as an input
        this.order = orderIn;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        super.startExecuting();

        logger.info("start executing called");

        ItemGroup remainingItems = order.getRemainingRequiredItems();

        // nothing to do
        if (remainingItems.isEmpty()) {
            return;
        }

        this.entityItemFetcher = new EntityItemFetcher(this.world, this.entity, this.entity.getInventory(), remainingItems);
    }


    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask() {

        logger.info("update task called");

        if (entityItemFetcher.hasFetchedAll()) {
            order.fill(order.getRemainingRequiredItems());
            setDone();
            return;
        }

        this.entityItemFetcher.continueFetching();
    }
}
