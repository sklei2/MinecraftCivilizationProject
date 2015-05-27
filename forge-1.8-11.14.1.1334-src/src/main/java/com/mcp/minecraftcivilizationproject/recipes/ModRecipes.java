package com.mcp.minecraftcivilizationproject.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.mcp.minecraftcivilizationproject.Reference;
import com.mcp.minecraftcivilizationproject.items.ModItems;


/**
 * This mod will register all the recipes in the mod with the gameRegistry
 * @author seank
 *
 */
public class ModRecipes {
	
	public static void registerRecipes(){
		registerShapelessCraftingRecipes();
		registerShapedCraftingRecipes();
		registerSmeltingRecipes();
	}
	
	private static void registerShapelessCraftingRecipes(){
		
	}
	
	private static void registerShapedCraftingRecipes(){
		
		// what you need for the different recipes.
    	Item redStoneTorchStack = Item.getItemFromBlock(Blocks.redstone_torch);
    	ItemStack gunpowderStack = new ItemStack(Items.gunpowder,3);
    	Item featherStack = Items.feather;
    	Item stringStack = Items.string;
		
		
		// This should make a recipe for the exploding arrow.
   	 
    	//what you get from the recipe
    	ItemStack explodingArrowStack = new ItemStack(ModItems.getModItems().get(0),2);
        	 
    	/*
    	 * To make, it should be...
    	 * |3 x gunpowder    | | |
    	 * |1 redstone torch | | |   -> 2 x Exploding Arrow
    	 * |1 feather        | | |
    	 */
    	GameRegistry.addRecipe(explodingArrowStack,
    			"x",
    			"y",
    			"z",
    			'x',gunpowderStack,'y',redStoneTorchStack,'z',featherStack);
    	
    	
    	// This should make a recipe for the exploding bow.
    	//what you get from it...
    	ItemStack explodingBowStack = new ItemStack(Item.getByNameOrId(Reference.MOD_ID + "_ExplodingBow"));
    	/*
    	 * To make, it should be...
    	 * |1 redstone torch |                 | |
    	 * |1 string         | 1 redstone torch| |   -> 1 ExplodingBow
    	 * |1 redstone torch |                 | | 
    	 */
    	GameRegistry.addRecipe(explodingBowStack,
    			"x",
    			"yx",
    			"x",
    			'x',redStoneTorchStack,'y',stringStack);
		
	}
	
	private static void registerSmeltingRecipes(){
		
	}
}
