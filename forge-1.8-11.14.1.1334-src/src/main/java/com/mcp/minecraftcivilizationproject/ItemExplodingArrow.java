package com.mcp.minecraftcivilizationproject;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemExplodingArrow extends Item {
	
	private final String name = "ExplodingArrow";
	
	public ItemExplodingArrow() {
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(CivilizationMod.MODID + "_" + name);
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public String getName(){
		return name;
	}
	
}
