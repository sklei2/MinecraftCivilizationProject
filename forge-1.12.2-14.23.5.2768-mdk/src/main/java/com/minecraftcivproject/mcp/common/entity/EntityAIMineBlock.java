package com.minecraftcivproject.mcp.common.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * This class is to mimic the "slow" breaking of a block, like the player, instead of insta-breaking it
 */

public class EntityAIMineBlock extends EntityAIBuild {

    /**
     * Main constructor
     *
     * @param worldIn
     * @param entityIn
     * @param blockIn
     */
    public EntityAIMineBlock(World worldIn, EntityLiving entityIn, Block blockIn) {
        super(worldIn, entityIn, blockIn);
    }


    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        return (this.entityPosition != this.newPos);
    }


    /**
            * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return(!world.getBlockState(pos).getBlock().equals(Blocks.AIR));
    }


    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting(){
        this.world.destroyBlock(pos,true);
    }


}
