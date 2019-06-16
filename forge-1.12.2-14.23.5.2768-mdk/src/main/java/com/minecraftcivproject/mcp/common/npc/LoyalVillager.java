package com.minecraftcivproject.mcp.common.npc;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;

public class LoyalVillager extends EntityVillager {

    public LoyalVillager(World worldIn) {
        super(worldIn);

    }
}


/*
We could do something here (in this class) like BlockBase and make this the wrapper for all types of "Loyal Villagers".
I think this is the way to go in the future but for now the SPECIFIC (i.e. setSize, setProfession, etc) constructor info
is populated here.
 */

// Note: "this" is a built in Java term that applies whatever is attached to it (through a dot) to that specific, instantaneous object that's created.