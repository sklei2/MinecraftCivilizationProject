package com.mcp.minecraftcivilizationproject.items;

import java.util.ArrayList;

import net.minecraft.item.Item;

public final class ModItems {

	public static ArrayList<Item> modItems;
	
	public static void createItems(){
		modItems.add(new ItemExplodingArrow());
		modItems.add(new ItemExplodingBow());
	}
	
	public static ArrayList<Item> getModItems(){
		return modItems;
	}
}
