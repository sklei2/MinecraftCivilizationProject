package com.minecraftcivproject.mcp.common.initialization.items;

import com.minecraftcivproject.mcp.MinecraftCivProject;
import com.minecraftcivproject.mcp.common.initialization.register.ItemRegisterer;
import com.minecraftcivproject.mcp.utils.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {    // MP: Class is somewhat comparable to a structure in C; String is a wrapper on a character array
    public ItemBase(String name, CreativeTabs tabs){
        this(name, tabs, 64);   // MP: Why is maxSize only an input down below and why is there two constructors here? Are we just giving two options/variants for this constructor to be called with?
    }       // MP: What I think the explanation is: there are two constructors for ItemBase.  If the maxSize (stack) is not specified the object is created using the above constructor. <- Yes, this is a quality of life thing where you don't have to specify the stack size if you don't want to (defaults to 64)

    public ItemBase(String name, CreativeTabs tabs, int maxSize){
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tabs);
        setMaxStackSize(maxSize);
    }

    @Override
    public void registerModels() {
        MinecraftCivProject.PROXY.registerItemRenderer(this, 0, "inventory");
    }

}

// MP: How did you know to put those 4 methods inside the bottom ItemBase constructor?? Were those just the ones you picked out of many?