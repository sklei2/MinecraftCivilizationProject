package com.mcp.minecraftcivilizationproject.items;

import com.mcp.minecraftcivilizationproject.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * This class will be used for the creation of Spears. And will hopefully drop
 * from leaves with saplings.
 * @author SKlei
 *
 */
public class ItemSturdyStick extends Item {

	private final String name = "SturdyStick";
	
	public ItemSturdyStick(){
		super();
		setUnlocalizedName(Reference.MOD_ID + "_" + name);
		GameRegistry.registerItem(this, this.getUnlocalizedName().substring(5));
		setCreativeTab(CreativeTabs.tabMaterials);
	}
}
