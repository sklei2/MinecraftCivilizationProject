package com.minecraftcivproject.mcp.common.entity;


import com.minecraftcivproject.mcp.common.initialization.register.LootTableRegisterer;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;


public class LoyalVillager extends EntityVillager {

    private boolean areAdditionalTasksSet;
    private Block blockOfInterest;    // This will eventually be what the resource manager is requesting for the current build (only applicable for builder class LVs -> no/low atk, high hp)


    public LoyalVillager(World worldIn) {
        super(worldIn);
        //this.setSize(1.8F, 6F);     // This doesn't seem to make a different for in-game model atm
        this.setBuildTask();
    }


    @Override
    protected void initEntityAI() {
        this.tasks.addTask(5, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.6D, true));   // Attack task -> the attack reach (this.getAttackReachSqr) is way too far
        this.tasks.addTask(3, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(1, new EntityAIBuild(world, this, Blocks.GLOWSTONE,0, 0,0)); // ERROR: WHEN THIS IS ACTIVATED THE LV CANNOT BE HIT/ACTIVATED
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));   // Don't know if this works because of EntityAIAttackMelee
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
    }


    private void setAdditionalAItasks(){        // This is where EntityAIBuild should be and where the desired block is read im
        if (!this.areAdditionalTasksSet)
        {
            this.areAdditionalTasksSet = true;

        }
    }


    public void setBuildTask(){

    }


    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();      // Does this take everything else from this method in the super class that's not overwritten below and apply it?
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);    // 2x default
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);  // 3x default
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(4.0D);  // 2x default -> this might be causing the server to overload and skip ticks
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D); // A bit more (default = 0.5D)
    }


    public void putItemInChest(Block block, BlockPos chestPos){
        //getRequestedItem(ItemBlock(block))
    }


    // Base attack off of Wolf at first (passive -> aggressive on getting attacked)
    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)      // "Attack LoyalVillager from damage source" -> I don't think we need to override this one (LV's take damage just like villagers for now!)
    {
        super.attackEntityFrom(source, amount);

        return super.attackEntityFrom(source, amount);
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag)
        {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }


    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootTableRegisterer.LOYAL_VILLAGER;
    }


    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
    }


    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        //this.setCombatTask();
    }


/*  USE TO REGISTER A SPAWNED-IN ENTITY
    public static void registerFixesWither(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityWither.class);
    }
*/


}


/*
We could do something here (in this class) like BlockBase and make this the wrapper for all types of "Loyal Villagers".
I think this is the way to go in the future but for now the SPECIFIC (i.e. setSize, setProfession, etc) constructor info
is populated here.
 */

// Note: "this" is a built in Java term that applies whatever is attached to it (through a dot) to that specific, instantaneous object that's created.