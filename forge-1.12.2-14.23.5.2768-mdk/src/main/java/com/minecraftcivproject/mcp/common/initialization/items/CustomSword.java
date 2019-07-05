package com.minecraftcivproject.mcp.common.initialization.items;

import com.minecraftcivproject.mcp.MinecraftCivProject;
import com.minecraftcivproject.mcp.utils.IHasModel;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class CustomSword extends ItemSword implements IHasModel {

    public CustomSword(String name, ToolMaterial material){
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    @Override
    public void registerModels() {
        MinecraftCivProject.PROXY.registerItemRenderer(this, 0, "inventory");
    }

    // Do I have to extend ItemBase further to something like "MaterialBase" to add more specific input parameters like rarity in the world, size/shape, color, etc.?
    // And furthermore, how would I add a new material entirely??? -> i.e. Crystal: harvest level 3, uses 2500, efficiency 9.0, damage 10.0, enchantability 10
}
