package com.mcp.minecraftcivilizationproject;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemExplodingArrow extends Item {
	
	// Name of the object
	private final String name = "ExplodingArrow";
	
	// the constructor will register the item and set its unlocalized name
	public ItemExplodingArrow() {
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(CivilizationMod.MODID + "_" + name);
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public String getName(){
		return name;
	}
	
}
