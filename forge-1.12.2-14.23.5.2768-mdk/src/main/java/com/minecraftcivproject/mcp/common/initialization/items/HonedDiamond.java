package com.minecraftcivproject.mcp.common.initialization.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class HonedDiamond extends ItemBase {

    public HonedDiamond(){
        super("honed_diamond",
                CreativeTabs.MATERIALS,
                64);

    }

    // TODO: Add crafting recipe (Simple: 8 buckets of lava around the outside and a stack of diamonds on the inside.  Advanced: Put the simple ingredients in a new item called the smelter - 9x9 furnace)

    // Is there any method that has to be called here per Minecraft source code (like onBlockAdded)?
    // Also, do I have to extend ItemBase further to something like "MaterialBase" to add more specific input parameters like rarity in the world, size/shape, color, etc.?
}
