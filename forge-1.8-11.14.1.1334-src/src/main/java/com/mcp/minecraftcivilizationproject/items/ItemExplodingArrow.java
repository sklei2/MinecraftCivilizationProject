package com.mcp.minecraftcivilizationproject.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.mcp.minecraftcivilizationproject.Reference;

public class ItemExplodingArrow extends Item {
	
	// Name of the object
	private final String name = "ExplodingArrow";
	
	// the constructor will register the item and set its unlocalized name
	public ItemExplodingArrow() {
		//super();
		setUnlocalizedName(Reference.MOD_ID + "_" + name);
		GameRegistry.registerItem(this, this.getUnlocalizedName().substring(5));
		setCreativeTab(CreativeTabs.tabCombat);
	}

	
}
