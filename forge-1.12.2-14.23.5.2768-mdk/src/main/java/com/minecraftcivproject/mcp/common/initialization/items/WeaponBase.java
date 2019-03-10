package com.minecraftcivproject.mcp.common.initialization.items;

import net.minecraft.creativetab.CreativeTabs;

public class WeaponBase extends ItemBase {

    public WeaponBase(){
        super("honed_diamond",
                CreativeTabs.MATERIALS,
                64);

    }

    // Is there any method that has to be called here per Minecraft source code (like onBlockAdded)?
    // Also, do I have to extend ItemBase further to something like "MaterialBase" to add more specific input parameters like rarity in the world, size/shape, color, etc.?
}
