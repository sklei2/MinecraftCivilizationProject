package com.mcp.minecraftcivilizationproject.items;

import java.util.ArrayList;

import net.minecraft.item.Item;

public final class ModItems {

	private static ArrayList<Item> modItems =  new ArrayList<Item>();
		
	public static void createItems(){
		modItems = new ArrayList<Item>();
		modItems.add(new ItemExplodingArrow());
		modItems.add(new ItemExplodingBow());
		modItems.add(new ItemSturdyStick());
		//modItems.add(new ItemSpear());
		modItems.add(new ItemBuildingSaver());
		
		
	}
	
	public static ArrayList<Item> getModItems(){
		return modItems;
	}
}
