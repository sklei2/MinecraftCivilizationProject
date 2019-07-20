package com.minecraftcivproject.mcp.common.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.logging.Logger;

public class EntityAIBuild extends EntityAIBase {

    private static Logger logger = Logger.getLogger("EntityAIBuild");
    private World world;
    private EntityLiving entity;
    private Block block;
    public BlockPos entityPosition;     // BlockPos pos; pos.getX() pos.getY() pos.getZ() returns that x,y,z position of a block
    public BlockPos pos;        // This is the position of the desired block within the search area
    private boolean blockIsFound;
    private int xLength = 5;
    private int yLength = 2;
    private int zLength = 5;


    public EntityAIBuild(World worldIn, EntityLiving entityIn, Block blockIn, int x, int y, int z){
        this.world = worldIn;           // Assigns the field of "world" to the object, aka a new EntityAIBuild almost like a class specific global variable (.field)
        this.entity = entityIn;         // It seems like if a field like this is created, it can be referenced by any method inside of this class without having it as an input
        this.block = blockIn;
    }

/* Need to:
1) Stop the movement of the LV
2) Get the current position of the LV
----- Covered in startExecuting() -----
3) Search a X x Y x Z region around the LV
----- Covered in updateTask() -----
4a) If nothing is found by the LV move 1 block North and try again (or expand the search volume from where its standing)
----- Covered in shouldKeepExecuting() -----
4b) If the block is found move towards it until 1 block away/touching it
5) Break the block and pick up the itemblock it drops
6) Take the itemblock back to the chest
 */

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
        logger.info("The entity has started the Build Task!");      // This is used for debugging to see order of method calls, will remove later
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return true;
    }


    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean shouldContinueExecuting()     // Is the "stop" to updateTask()?? -> THIS IS NOT ENDING THE TASK EVEN IF FED "False"
    {
        logger.info("The search continues...");
        return(!blockIsFound);
        /*
        if(blockCheck(this.pos,this.entityPosition)){
            BlockPos newPos = entityPosition.north();
            this.entityPosition = this.moveEntity(entity, newPos, entityPosition);
            return true;
        }
        else{
            return false;
        }
        */
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        //this.stopEntityMovement();    // This is a java method call (the this. isn't necessary, it just emphasizes this method is for the current object instance
        //logger.info("The entity stopped moving.");
        //try {
        //    Thread.sleep(2000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}

        this.entityPosition = this.getEntityPosition();
        logger.info("The entity is at " + entityPosition.getX() + "," + entityPosition.getY() + "," + entityPosition.getZ());


        SearchArea area = new SearchArea(xLength,yLength,zLength);
        this.pos = area.searchFor(world, block, entityPosition);

        if(this.pos.equals(this.entityPosition)){

            logger.info("A block of " + block.getLocalizedName() + " was not found...");

            BlockPos newPos = entityPosition.north();
            this.entityPosition = this.moveEntity(entity, newPos, entityPosition);    // Does this just return the position if the LV can get there? (found in EntityAIMoveIndoors) It might actually set the pat for travel (not sure how that feeds into actually moving the entity....)
            this.entity.move(MoverType.SELF, newPos.getX(), newPos.getY(), newPos.getZ());  // LOOK MORE INTO THIS METHOD: I think line 1035 is where the entity start to move

            this.blockIsFound = false;
            logger.info("BlockIsFound set to " + blockIsFound);

        }
        else{

            logger.info("A block of " + block.getLocalizedName() + " was found!");

            int newPosX = pos.getX()-1;
            int newPosY = pos.getY();
            int newPosZ = pos.getZ()-1;
            logger.info("Entity's path is set to " + newPosX + "," + newPosY + "," + newPosZ);
            this.entity.getNavigator().tryMoveToXYZ((double)newPosX, (double)newPosY, (double)newPosZ, 1.0D);

            // Must delay return statement until the entity reaches the block.  There must be a better way to do this...
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.blockIsFound = true;       // Should I send this when the block is found? Wouldn't this end the task when we still need to break the block?
            logger.info("BlockIsFound set to " + blockIsFound);

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


    public void stopEntityMovement() {
        this.entity.motionX = 0.0D;
        this.entity.motionY = 0.0D;
        this.entity.motionZ = 0.0D;
    }

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


    public BlockPos getEntityPosition() {    // DOES THIS EVEN DO ANYTHING??? I LITERALLY PULLED THIS OUT OF THIN AIR AND THIS METHOD IS NOT DEFINED ANYWHERE ELSE - also, this.entity.posX is saying it's a de-reference of this.entity and could lead to a NullPointer - is this. whatever necessary here?
        return new BlockPos(this.entity.posX, this.entity.posY + (double)this.entity.height, this.entity.posZ);     // posX, posY and posZ are the positions of the entity exist in Entity (where those fields are assigned to NBT memory)
    }


    // Try move(MoverType, x, y, z) -> in Entity
    public BlockPos moveEntity(Entity entity, BlockPos newPos, BlockPos entityPosition){
        int x = newPos.getX();
        int y = newPos.getY();
        int z = newPos.getZ();
        this.entity.getNavigator().tryMoveToXYZ((double)x + 0.5D, (double)y, (double)z + 0.5D, 1.0D);   // In EntityAIMoveIndoors, the x and z components have 0.5D added to them, maybe to make the entity actually go through the threshold?

        return new BlockPos(this.entity.posX, this.entity.posY + (double)this.entity.height, this.entity.posZ);
    }





    //Block.getBlockFromName() ?

    /*
    IBlockState current = getCurrentBlock();


    public IBlockState getCurrentBlock()
    {
        return getWorld().getBlockState(getPos());
    }
    */


    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask(){     // This seems to be called right after shouldContinueExecuting() - HOW DO I END THIS TASK????
        this.entity.getNavigator().clearPath();
        logger.info("The task has been reset.");
    }
}
