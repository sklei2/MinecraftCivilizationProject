package com.minecraftcivproject.mcp.common.npc;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;

public class LoyalVillager extends EntityVillager {

    public LoyalVillager(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);    // 2x default
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);  // 3x default
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);  // 2x default
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0D); // More
    }
}


/*
We could do something here (in this class) like BlockBase and make this the wrapper for all types of "Loyal Villagers".
I think this is the way to go in the future but for now the SPECIFIC (i.e. setSize, setProfession, etc) constructor info
is populated here.
 */

// Note: "this" is a built in Java term that applies whatever is attached to it (through a dot) to that specific, instantaneous object that's created.