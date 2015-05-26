package com.mcp.minecraftcivilizationproject.items;

import com.mcp.minecraftcivilizationproject.CivilizationMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemExplodingArrow extends Item {
	
	// Name of the object
	private final String name = "ExplodingArrow";
	
	// the constructor will register the item and set its unlocalized name
	public ItemExplodingArrow() {
		super();
		setUnlocalizedName(CivilizationMod.MODID + "_" + name);
		GameRegistry.registerItem(this, name);
		setCreativeTab(CreativeTabs.tabCombat);
	}
	
	public String getName(){
		return name;
	}
	
}
