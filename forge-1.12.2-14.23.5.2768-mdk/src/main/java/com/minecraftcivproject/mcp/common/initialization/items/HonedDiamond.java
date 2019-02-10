package com.minecraftcivproject.mcp.common.initialization.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class HonedDiamond extends ItemBase {    // Class is somewhat comparable to a structure in C; String is a wrapper on a character array

    public HonedDiamond(){
        super("honed_diamond",
                CreativeTabs.MATERIALS,
                64);

    }

    // Is there any method that has to be called here per Minecraft source code (like onBlockAdded)?
    // Also, do I have to extend ItemBase further to something like "MaterialBase" to add more specific input parameters like rarity in the world, size/shape, color, etc.?
}
