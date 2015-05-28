package com.mcp.client.render.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

import com.mcp.minecraftcivilizationproject.Reference;
import com.mcp.minecraftcivilizationproject.items.ItemExplodingBow;
import com.mcp.minecraftcivilizationproject.items.ModItems;

public final class ItemRenderRegister {

	public static String modid = Reference.MOD_ID;
	
	public static void registerItemRenderer(){
		// for all the mod items register the rendering.
		for(Item i : ModItems.getModItems()){
			try{
				reg(i);
			}catch(NullPointerException e){
				
			}
		}
	}
	
	public static void reg(Item i){
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(i, 0, new ModelResourceLocation(modid + ":" + i.getUnlocalizedName().substring(5),"inventory"));
		
		if(i instanceof ItemExplodingBow){
			ModelBakery.addVariantName(i, 
					modid + ":" + i.getUnlocalizedName().substring(5),
					modid + ":" + i.getUnlocalizedName().substring(5) + "_pulling0",
					modid + ":" + i.getUnlocalizedName().substring(5) + "_pulling1",
					modid + ":" + i.getUnlocalizedName().substring(5) + "_pulling2");
		}

	}
		
}
