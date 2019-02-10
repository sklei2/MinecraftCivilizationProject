package com.minecraftcivproject.mcp.common.initialization.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item {    // class is somewhat comparable to a structure in C; String is a wrapper on a character array
    public ItemBase(String name, CreativeTabs tabs){
        this(name, tabs, 64);
    } // constructor (public constructors can be used anywhere in the code)

    public ItemBase(String name, CreativeTabs tabs, int maxSize){
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tabs);
        setMaxStackSize(maxSize);
    }
}
