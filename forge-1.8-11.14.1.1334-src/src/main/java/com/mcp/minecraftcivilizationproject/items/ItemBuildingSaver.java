package com.mcp.minecraftcivilizationproject.items;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import com.mcp.minecraftcivilizationproject.Reference;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBuildingSaver extends Item{
	// Name of the object
		private final String name = "BuildingSaver";
		
		// the constructor will register the item and set its unlocalized name
		public ItemBuildingSaver() {
			//super();
			setUnlocalizedName(Reference.MOD_ID + "_" + name);
			GameRegistry.registerItem(this, this.getUnlocalizedName().substring(5));
			setCreativeTab(CreativeTabs.tabCombat);
		}
		
		@Override
		 public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	    {
			this.saveBuilding(worldIn, pos);
	        return false;
	    }
		
		public void saveBuilding(World worldIn, BlockPos initialPos){
			int posX = initialPos.getX();
			int posY = initialPos.getY();
			int posZ = initialPos.getZ();
			BlockPos pos;
			String s = "";
			int[][][] building = new int[10][10][10];
			for(int x = (int)posX; x < (int)posX + 10; ++x){
				for(int y = (int)posY; y < (int)posY + 10; ++y){
					for(int z = (int)posZ; z < (int)posZ + 10; ++z){
						pos = new BlockPos(x, y, z);
						building[x - (int)posX][y - (int)posY][z - (int)posZ] = Block.getStateId((worldIn.getBlockState(pos)));
						s += building[x - (int)posX][y - (int)posY][z - (int)posZ] + " ";
					}
					s += "\n";
				}
				s+= "\n";
			}
			System.out.println(s);
		}

}
