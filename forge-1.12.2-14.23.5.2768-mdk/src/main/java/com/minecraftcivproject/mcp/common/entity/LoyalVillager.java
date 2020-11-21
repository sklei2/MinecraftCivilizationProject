package com.minecraftcivproject.mcp.common.entity;


import com.minecraftcivproject.mcp.common.entity.task.core.Task;
import com.minecraftcivproject.mcp.common.entity.task.core.manager.TaskAdapter;
import com.minecraftcivproject.mcp.common.initialization.register.LootTableRegisterer;
import com.minecraftcivproject.mcp.utils.Inventory;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.logging.Logger;


public class LoyalVillager extends EntityVillager {

    private static Logger logger = Logger.getLogger("LoyalVillager");
    private final String name;
    private Inventory inventory;
    private boolean areAdditionalTasksSet;
    //blockOfInterest will eventually be what the resource manager is requesting for the current build (only applicable for
    //builder class LVs -> no/low atk, high hp)
    private Block blockOfInterest;
    private IBlockState state;
    // this.world.getClosestPlayerToEntity - this could be useful in the future
    public boolean buildStuff;

    private TaskAdapter topLevelTask;


    /**
     * Main constructor
     */
    public LoyalVillager(World worldIn) {
        super(worldIn);
        this.name = UUID.randomUUID().toString();  // Unique ID of a loyal villager
        this.setProfession(5);  // Sets profession to nitwit lol
        //this.setSize(1.8F, 6F);  // This doesn't seem to make a difference for in-game model atm...
        this.blockOfInterest = Blocks.COBBLESTONE;
        this.buildStuff = true;

        logger.info("LoyalVillager constructor called, this entity is " + this);

        inventory = new Inventory(this.name, true, 64);
    }


    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
//        if (!this.isDead) {
//            this.pickupItem(this.blockOfInterest.getItemDropped(state, new Random(), 0));  // This is a very hardcodey call
//        }
    }

    public void assignTask(Task task){
        this.topLevelTask.addTask(task);
    }


    @Override
    protected void initEntityAI() {  // THIS IS CALLED BEFORE THE CONSTRUCTOR WTF!!!!

        logger.info("LoyalVillager tasks initialized " + this);

        this.topLevelTask = new TaskAdapter()
                .addTask(new EntityAISwimming(this))
                .addTask(new EntityAIAttackMelee(this, 0.6D, true))
                .addTask(new EntityAIOpenDoor(this, true))
                .addTask(new EntityAIWanderAvoidWater(this, 0.6D))
                .addTask(new EntityAIHurtByTarget(this, false, new Class[0]))
                .addTask(new EntityAINearestAttackableTarget(this, EntityPlayer .class, true))
                .addTask(new EntityAINearestAttackableTarget(this, EntityIronGolem .class, true));

        this.tasks.addTask(1, topLevelTask);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();      // Does this take everything else from this method in the super class that's not overwritten below and apply it?
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1.0D);    // 2x default >> 40.0D >> 1.0D for testing
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);  // 3x default
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(4.0D);  // 2x default -> this might be causing the server to overload and skip ticks
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D); // A bit more (default = 0.5D)
    }

    // Based attack off of Wolf at first (passive -> aggressive on getting attacked)
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

    @Override
    public void onDeath(DamageSource dmgSrc) {
        super.onDeath(dmgSrc);
        //this.inventory.dropAllOnGround;
    }


    public Inventory getInventory() {
        return this.inventory;
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