package com.minecraftcivproject.mcp.common.entity;

import com.minecraftcivproject.mcp.common.queueable.Order;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;


public class EntityAIBuild extends EntityAIBase {

    private static Logger logger = Logger.getLogger("EntityAIBuild");
    public World world;  // This is a pointer b/c it's not a primitive type
    private LoyalVillager entity;
    private Order order;
    private Block block;
    private Block currentBlock;
    private Item currentItem;
    private ArrayList<Boolean> checkList;
    Iterator<Block> blockItr;
    private boolean go;
    public BlockPos entityPos;  // BlockPos pos; pos.getX() pos.getY() pos.getZ() returns that x,y,z position of a block
    public BlockPos pos;  // This is the position of the desired block within the search area
    public BlockPos newPos;  // This is the new position the entity should move to
    private SearchArea area;
    private int xLength = 10;
    private int yLength = 1;  // For some reason it only works if this is 1...
    private int zLength = 10;
    private int breakDelay = 0;
    public EntityAIMineBlock mineBlock;  // Task to add, can this be added within this class???
    private boolean successOff = true;
    public LVInventory inventory;
    private IBlockState state;


    /**
     * Constructor
     */
//    public EntityAIBuild(World worldIn, LoyalVillager entityIn, Block blockIn, InventoryBasic inventory, boolean doTheThing) {  // THEN THIS IS CALLED NEXT, FOLLOWED BY THE LV CONSTRUCTOR BEING CALLED... THIS MEANS THE INVENTORY IS SET TO NULL BECAUSE THE F'KING LV HASN'T EVEN BEEN CREATED YET!!!
//        this(worldIn, entityIn, blockIn, doTheThing);
//        this.inventory = inventory;
//        logger.info("EntityAIBuild constructor called, entity inventory is set to " + this.inventory + " created from " + inventory);
//    }

    public EntityAIBuild(World worldIn, LoyalVillager entityIn, Order orderIn, boolean doTheThing) {  // THIS SEEMS TO BE CALLED FIRST
        this.world = worldIn;  // Assigns the field/attribute of "world" to the object, aka a new EntityAIBuild almost like a class specific global variable (.field)
        this.entity = entityIn;  // It seems like if a field/attribute like this is created, it can be referenced by any method inside of this class without having it as an input
        this.order = orderIn;
        this.go = doTheThing;
//        logger.info("EntityAIBuild constructor called");
    }



    /** TODO: -> SOMEWHERE IN HERE IS A NULL POINTER, I THINK IT'S THE ORDER SINCE IT IS CREATED AFTER initEntityAI IS CALLED.....
    1) Stop the movement of the LV - not working currently
    2) Get the current position of the LV
    ----- Covered in startExecuting() -----
    3) Search a X x Y x Z region around the LV
    ----- Covered in startExecuting() -----
    4a) If nothing is found by the LV move 1 block North and try again (or expand the search volume from where its standing)
    ----- Covered in updateTask() -----
    ----- Covered in shouldKeepExecuting() -----
    4b) If the block is found, move towards it until 1 block away/touching it
    ----- Covered in updateTask() -----
    5) Break the block and pick up the itemblock it drops
    ----- Covered in updateTask() -----
    6) Take the itemblock back to the chest
    7) Repeat until order is satisfied
     */



    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {  // Should be conditional on the search, or should that be shouldContinue executing? I think it's the latter
        logger.info("shouldExecute called");  // This is used for debugging to see order of method calls, will remove later

//        logger.info("Inside EntityAIBuild, the entityIn is " + this.entity);
//        logger.info("EntityIn's inventory is " + this.entity.getInventory());

        if (this.go) {
            this.inventory = this.entity.getInventory();
//            logger.info("Inventory has been set to " + this.inventory);
//            logger.info("Item in slot 1 is " + this.inventory.getStackInSlot(0) + ". Max size of inventory is " + this.inventory.getSizeInventory());
            checkList = checkInventoryForOrder(order);
            if (this.order.isFilled(checkList)) {
                return false;
            }

            blockItr = order.getBlockList().iterator();
            currentBlock = blockItr.next();  // Set current block to look for
            currentItem = currentBlock.getItemDropped(state, new Random(), 0);

            boolean execute = this.entity.buildStuff;
            this.entity.buildStuff = false;  // Hardcodey way to make this is only done once ;)
            return execute;
        }
        else {
            return false;

        }
    }


    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     * DOES THIS CONNECT TO updateTask()??? It looks like it does... which begs the question WHAT IN THE FUCK STOPS THE EntityAIBuild TASK???????
     */
    @Override
    public boolean shouldContinueExecuting() {
        logger.info("shouldContinueExecuting called");
//        logger.info("continueTask is " + continueTask);

        if (!this.order.isFilled()) {
            logger.info("The order has not filled yet");
            for (int i = 0; i < order.getBlockList().size(); i++) {
                if (checkList.get(i)) {
                    logger.info("The resource order of the current block (" + currentBlock.getLocalizedName() + ") has been filled!");
                    currentBlock = blockItr.next();  // Move on to the next block in the list
                    logger.info("The current block has changed. It is now " + currentBlock.getLocalizedName());
                }
                else {
                    // This entry in checkList is false, meaning this resource order has been filled
                    logger.info("The resource order of the current block (" + currentBlock.getLocalizedName() + ") has not been filled yet");
                    return true;
                }
            }
        }
        return false;   // Order should be satisfied if this return statement is hit
    }


    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        logger.info("startExecuting called");

        //this.stopEntityMovement();    // INFO: This is a java method call (the this. isn't necessary, it just emphasizes this method is for the current object instance
//        logger.info("The entity stopped moving.");

        this.entityPos = this.getEntityPos();  // this.entity.getPosition() does the same thing but returns eye-level position
        logger.info("The entity is starting at " + entityPos.getX() + "," + entityPos.getY() + "," + entityPos.getZ());

        area = new SearchArea(xLength, yLength, zLength);  // Should a new search area be created here or in update task???
        area.search(world, currentBlock, entityPos);
        this.pos = area.getBlockPos();
    }


    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask() {
//        logger.info("updateTask called");

        logger.info("this.pos = " + this.pos);
        logger.info("this.entityPos = " + this.entityPos);


        if (!area.wasBlockFound()) {

            logger.info("A block of " + currentBlock.getLocalizedName() + " was not found...");

            BlockPos newPos = entityPos.east(); // This is fine for now but will need updated...
            //this.entityPosition = this.moveEntity(entity, newPos, entityPosition);    // Does this just return the position if the LV can get there? (found in EntityAIMoveIndoors) It might actually set the pat for travel (not sure how that feeds into actually moving the entity....)
            this.entity.getNavigator().tryMoveToXYZ((double)-newPos.getX(), (double)-newPos.getY(), (double)-newPos.getZ(), 0.8D);  // The minus is there to move in the -X direction

            area.search(world, currentBlock, this.getEntityPos());
            this.pos = area.getBlockPos();
        }

        else {

            //BlockPos originalPosition = this.entityPosition;

            int newPosX = this.pos.getX();
            int newPosY = this.pos.getY();
            int newPosZ = this.pos.getZ()-1;  // Makes the entity stand one block away from the block... this should change based on what direction the entity is coming from
            this.newPos = new BlockPos(newPosX,newPosY,newPosZ);

            logger.info("Moving to the block of " + currentBlock.getLocalizedName() + " found at " + newPosX + "," + newPosY + "," + (newPosZ+1) +"...");

//            logger.info("Entity's path is set to " + newPosX + "," + newPosY + "," + newPosZ);
            this.entity.getNavigator().tryMoveToXYZ((double)newPosX, (double)newPosY, (double)newPosZ, 0.8D);
            //this.entity.getNavigator().tryMoveToXYZ((double)originalPosition.getX(), (double)originalPosition.getY(), (double)originalPosition.getZ(), 0.8D);

            this.entityPos = this.getEntityPos();     // Is this needed to update the entityPosition field?  It doesn't seem to be updating...
//            logger.info("Entity: " + this.entityPosition + " New Pos: " + this.newPos);


            // *** Attempt 3 ***                                // <--- THIS DOES SOLVE THE ISSUE OF THE TASK COMPLETELY REPEATING ITSELF IF THIS CONDITION IS NOT TRUE
            if (entityPos.getX() == newPos.getX() && entityPos.getY() == newPos.getY() && entityPos.getZ() == newPos.getZ()) {        // NOTE: entityPosition == newPos DID NOT WORK (WHY I WILL NEVER KNOW) BUT THIS SHIT DOES!!!
                if (successOff) {
                    logger.info("The logic worked!!!!!");
                    //logger.info(this.toString());  // Doesn't output anything...
                    successOff = false;
                }
                world.destroyBlock(pos,true);  // TODO: This should be replaced by EntityAIMineBlock in the future!
//                this.continueTask = false;  // This doesn't seem it do anything... the LV just keeps calling updateTask...

//                //this.block.getItemDropped(state, new Random(), 0);  // These parameters are arbitrary, I hope this doesn't mess anything up... they really aren't used for anything so it shouldn't...
                AxisAlignedBB axisalignedbb = this.entity.getEntityBoundingBox().grow(1.0D, 0.5D, 1.0D);  // Creates an axis-aligned bounding box around the entity
                List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this.entity, axisalignedbb);

                for(int i = 0; i < list.size(); ++i) {
                    Entity entity = list.get(i);
                    if (entity instanceof EntityItem) {
                        logger.info("~*~*~*~*~ " + entity.getName() + " was found at " + entity.getPosition() + "! ~*~*~*~*~");
//                        //this.entity.onCollideWithPlayer(entity);  // This cannot be done b/c the entity is not an EntityPlayer...
                        EntityItem entityItem = (EntityItem)entity;  // Must cast entity into an EntityItem even if it's already an EntityItem to be able to call EntityItem methods on it!
                        ItemStack itemStack = entityItem.getItem();
                        Item item = itemStack.getItem();
                        logger.info("~*~*~*~*~ The item on the ground is " + item + " ~*~*~*~*~");

//                        logger.info("The entity's inventory is " + this.inventory);
                        ItemStack newItemStack = this.inventory.addItem(itemStack);  // Adds item to inventory
                        entityItem.setDead();  // Destroys the item block on the ground
                        logger.info("Item in slot 1 is " + this.inventory.getStackInSlot(0) + ". Max size of inventory is " + this.inventory.getSizeInventory());
                        this.order.add(item, itemStack.getCount());  // HOW DO YOU TRANSLATE WHAT ITEM CAME FROM WHAT BLOCK??? -> through a giant json...
                    }
                }

                //this.entity.setDead();
                this.entityPos = this.getEntityPos();  // Updates entity's registered position with it's current position
                SearchArea area = new SearchArea(xLength, yLength, zLength);
                area.search(world, currentBlock, this.getEntityPos());
                this.pos = area.getBlockPos();
                //return;
            }
            else {
                logger.info("The entity has not reached the block yet.....");
            }




            // *** Attempt 2 ***
            //this.entity.tasks.addTask(1, this.mineBlock);  // This causes the game to crash...


            // *** Attempt 1 ***
            // Without this while loop, the entity moves to the correct position (newPos)
            /*while (this.entityPosition != this.newPos) {
                this.entityPosition = this.getEntityPosition();    // Updates this.entityPosition
                logger.info((this.entityPosition == this.newPos) + " ---> entityPosition = " + entityPosition + ", newPos = " + newPos);  // For debugging

                if (this.entityPosition == this.newPos) {
                    world.destroyBlock(pos,true);
                    break;
                }
            }*/

            
            /**
                *Pick up item* -> this.setCanPickUpLoot? Or do villagers already have this set?

                *Move back to chest location*

                *Put item in chest*

                *Repeat until the request is satisfied*
             */



            //this.continueTask = true;   // This seems to restart just this portion of updateTask()...
//            logger.info("continueTask was set to true!");

        }
    }


    /**
     * Keep ticking a continuous task that has already been started
     */
    /*
    public void updateTask()
    {
        SearchArea area = new SearchArea(xLength,yLength,zLength);
        this.pos = area.searchFor(world, block, entityPosition);

        if(this.pos.equals(this.entityPosition)){

            logger.info("A block was not found...");

            BlockPos newPos = entityPosition.north();
            this.entityPosition = this.moveEntity(entity, newPos, entityPosition);    // Does this just return the position if the LV can get there? (found in EntityAIMoveIndoors) It might actually set the pat for travel (not sure how that feeds into actually moving the entity....)
            this.entity.move(MoverType.SELF, newPos.getX(), newPos.getY(), newPos.getZ());  // LOOK MORE INTO THIS METHOD: I think line 1035 is where the entity start to move

            this.blockIsFound = false;

        }
        else{

            logger.info("A block was found!");

            this.entityPosition = this.moveEntity(entity, pos, entityPosition);       // Same comment as above ^
            this.entity.move(MoverType.SELF, pos.getX()-1, pos.getY()-1, pos.getZ()-1);     // SHOULD move the entity right next to (a block away from) the desired block)

            this.blockIsFound = true;       // Should I send this when the block is found? Wouldn't this end the task when we still need to break the block?

        }


        // Use this if this.pos.equals(this.entityPosition) doesn't work
        /*
        private int x = pos.getX();
        private int y = pos.getY();
        private int z = pos.getZ();
        private int X = entityPosition.getX();
        private int Y = entityPosition.getY();
        private int Z = entityPosition.getZ();
        private int posCheck = (x-X)+(y-Y)+(z-Z);

    }*/

/*
    public void stopEntityMovement() {
        this.entity.motionX = 0.0D;
        this.entity.motionY = 0.0D;
        this.entity.motionZ = 0.0D;
    }*/


/*
    public boolean blockCheck(BlockPos pos, BlockPos startingLocation) {
        if (pos.equals(startingLocation)) {
            return true;
            // Then continue to loop through the whole thing again... maybe use a while loop instead of a method?
        } else {                           // ERROR: Unknown token???
            BlockPos newPos = pos;
            BlockPos newEntityPosition = this.moveEntity(entity, newPos, entityPosition);    // This is suppose to be the function call to move the entity... should eventually be it's own class -> OR it already exists somewhere in the code
            // Perform breaking of block directly in front of entity...
            return false;
        }
    }
    */


    public BlockPos getEntityPos() {    // DOES THIS EVEN DO ANYTHING??? I LITERALLY PULLED THIS OUT OF THIN AIR AND THIS METHOD IS NOT DEFINED ANYWHERE ELSE - also, this.entity.posX is saying it's a de-reference of this.entity and could lead to a NullPointer - is this. whatever necessary here?
        return new BlockPos(this.entity.posX, this.entity.posY, this.entity.posZ);  // posX, posY and posZ are the positions of the entity (exists in Entity, where those fields are assigned to NBT memory)
        // Adding the entity height (+ (double)this.entity.height) to posY seems to make the position of the entity at its head block rather than its feet block
    }


    // Try move(MoverType, x, y, z) -> in Entity
    public BlockPos moveEntity(Entity entity, BlockPos newPos, BlockPos entityPosition){
        int x = newPos.getX();
        int y = newPos.getY();
        int z = newPos.getZ();
        this.entity.getNavigator().tryMoveToXYZ((double)x + 0.5D, (double)y, (double)z + 0.5D, 1.0D);   // In EntityAIMoveIndoors, the x and z components have 0.5D added to them, maybe to make the entity actually go through the threshold?

        return new BlockPos(this.entity.posX, this.entity.posY + (double)this.entity.height, this.entity.posZ);
    }


    public ArrayList<Boolean> checkInventoryForOrder(Order order) {
        int size = order.getItemList().size();
        ArrayList<Boolean> checkList = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            Item item = order.getItemList().iterator().next();
            checkList.add(this.order.getQuantity(item) == this.inventory.findAll(item));
        }
        return checkList;
    }




    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    /*public void resetTask(){     // This seems to be called right after shouldContinueExecuting
        //this.entity.getNavigator().clearPath();
        this.entityPosition = this.getEntityPosition();
        logger.info("The task has been interrupted and the state has been reset.");
    }*/


    // HOW DO I END THIS TASK????
    // removeTask seems to be for a task with a task -> this is a indicator to maybe break up the moveTo and breakBlock tasks
}
