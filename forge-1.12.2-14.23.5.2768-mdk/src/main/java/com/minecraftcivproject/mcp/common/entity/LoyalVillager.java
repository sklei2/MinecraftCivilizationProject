package com.minecraftcivproject.mcp.common.entity;


import com.minecraftcivproject.mcp.common.initialization.register.LootTableRegisterer;
import com.minecraftcivproject.mcp.common.queueable.Order;
import com.minecraftcivproject.mcp.server.managers.resource.ItemGroup;
import com.minecraftcivproject.mcp.server.managers.resource.ItemRequest;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.logging.Logger;


public class LoyalVillager extends EntityVillager {

    private static Logger logger = Logger.getLogger("LoyalVillager");
    private final String name;
    private final LVInventory inventory;
    private boolean areAdditionalTasksSet;
    //blockOfInterest will eventually be what the resource manager is requesting for the current build (only applicable for
    //builder class LVs -> no/low atk, high hp)
    private Block blockOfInterest;
    private IBlockState state;
    // this.world.getClosestPlayerToEntity - this could be useful in the future
    public boolean buildStuff;


    /**
     * Main constructor
     */
    public LoyalVillager(World worldIn) {
        super(worldIn);
        this.name = UUID.randomUUID().toString();  // Unique ID of a loyal villager
        this.setProfession(5);  // Sets profession to nitwit lol
        this.inventory = new LVInventory(this.name, true, 64);
//        logger.info("LoyalVillager constructor called, inventory is set to " + this.inventory);

        //this.setSize(1.8F, 6F);  // This doesn't seem to make a difference for in-game model atm...
        this.blockOfInterest = Blocks.COBBLESTONE;
        this.buildStuff = true;

//        logger.info("LoyalVillager constructor called, this entity is " + this);
    }


    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
//        if (!this.isDead) {
//            this.pickupItem(this.blockOfInterest.getItemDropped(state, new Random(), 0));  // This is a very hardcodey call
//        }
    }


    @Override
    protected void initEntityAI() {  // THIS IS CALLED BEFORE THE CONSTRUCTOR WTF!!!!
        logger.info("initEntityAI called");
        Order order = createOrder();
//        logger.info("Order created: " + order);

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.6D, true));  // Attack task -> the attack reach (this.getAttackReachSqr) is way too far
        this.tasks.addTask(3, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(5, new EntityAIBuild(world, this, order, true));  // ERROR: WHEN THIS IS ACTIVATED THE LV CANNOT BE HIT/ACTIVATED (might have to configure resetTask() correctly)
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));   // Don't know if this works because of EntityAIAttackMelee
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
        this.setAdditionalAItasks();
    }


    /*
    This is where EntityAIBuild should be (in the future) and where the desired block is read in
     */
    private void setAdditionalAItasks() {
        if (!this.areAdditionalTasksSet)
        {
            this.areAdditionalTasksSet = true;

            /* if (there is a chest placed within the vicinity of the LV (aka the bounding box of BlockPos.getAllInBox)){
                    this.setBuildTask;
            }
            */

        }
    }


    public void setBuildTask() {
        // this.tasks.addTask(2, new EntityAIBuild(world, this, Blocks.COBBLESTONE));
    }


    // This is a temporary class
    public Order createOrder() {
        logger.info("createOrder called");
//        Map<Block, Integer> map = new HashMap<>();
//        map.put(Blocks.COBBLESTONE, 2);
        ItemGroup cobblestone = new ItemGroup();
        cobblestone.add(Item.getItemFromBlock(Blocks.COBBLESTONE), 2);

        return new Order(new ItemRequest(cobblestone));
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


    public void putItemInChest(Block block, BlockPos chestPos){
        //getRequestedItem(ItemBlock(block))
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


    public LVInventory getInventory() {
        return this.inventory;
    }


    public boolean isWillingToPickupItem(Item itemOfInterest, EntityItem itemOnGround) {
        Item item = itemOnGround.getItem().getItem();
        return itemOfInterest == item;
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