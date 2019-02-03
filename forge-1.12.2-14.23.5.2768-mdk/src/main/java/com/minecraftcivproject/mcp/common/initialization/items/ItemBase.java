package com.minecraftcivproject.mcp.common.initialization.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item {
    public ItemBase(String name, CreativeTabs tabs){
        this(name, tabs, 64);
    }

    public ItemBase(String name, CreativeTabs tabs, int maxSize){
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tabs);
        setMaxStackSize(maxSize);
    }
}
