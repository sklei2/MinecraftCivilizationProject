package com.mcp.minecraftcivilizationproject.items;

import com.mcp.minecraftcivilizationproject.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSnowball;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemJavelin extends ItemSnowball {
	
	// Name of the item
	private final String name = "Javelin";
	//private final Item.ToolMaterial javelinMaterial;
	//private float attackDamage;
	
	// Lets have it so there is only one kind of javelin a "stone" javelin
	public ItemJavelin(){
		super();
		//this.javelinMaterial = material;
		//this.attackDamage = 2.0F + material.getDamageVsEntity();
		setUnlocalizedName(Reference.MOD_ID + "_" + name);
		setCreativeTab(CreativeTabs.tabCombat);
		GameRegistry.registerItem(this, getUnlocalizedName().substring(5));
	}

}
