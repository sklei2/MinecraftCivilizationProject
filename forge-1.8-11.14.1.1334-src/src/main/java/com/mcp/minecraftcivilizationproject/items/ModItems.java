package com.mcp.minecraftcivilizationproject.items;

import java.util.ArrayList;

import net.minecraft.item.Item;

public final class ModItems {

	public static ArrayList<Item> modItems = new ArrayList<Item>();

	public static void createItems(){
		modItems.add(new ItemExplodingArrow()); // index 0
		modItems.add(new ItemExplodingBow()); // 1
		modItems.add(new ItemSturdyStick()); // 2
		modItems.add(new ItemSpear(Item.ToolMaterial.STONE)); // 3
		modItems.add(new ItemSpear(Item.ToolMaterial.IRON)); // 4
		// really a diamond spear. Yes, I know it says "EMERALD"
		modItems.add(new ItemSpear(Item.ToolMaterial.EMERALD)); // 5
		modItems.add(new ItemSpear(Item.ToolMaterial.GOLD)); // 6
		
	}
	
	public static ArrayList<Item> getModItems(){
		return modItems;
	}
}
