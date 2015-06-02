package com.mcp.minecraftcivilizationproject.items;

import com.mcp.minecraftcivilizationproject.Reference;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSpear extends ItemSword {

	private float attackDamage;
	private Item.ToolMaterial spearMaterial;
	private String name = "Spear";
		
	public ItemSpear(ToolMaterial material) {
		super(material);
		spearMaterial = material;
		this.setUnlocalizedName(Reference.MOD_ID + "_" + name + "_" + this.spearMaterial.name());
		this.setCreativeTab(CreativeTabs.tabCombat);
		GameRegistry.registerItem(this, getUnlocalizedName().substring(5));
		this.attackDamage = 3.0F + material.getDamageVsEntity();
	}
	
}
