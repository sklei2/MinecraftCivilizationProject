package com.minecraftcivproject.mcp.common.initialization.blocks;

import com.minecraftcivproject.mcp.common.initialization.items.Crystal;
import com.minecraftcivproject.mcp.common.initialization.register.ItemRegisterer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.Random;

public class CrystalOre extends BlockBase{

    public CrystalOre(){
        super("crystal_ore",
                Material.ROCK,
                CreativeTabs.BUILDING_BLOCKS,
                10F,
                20F,
                "pickaxe",
                3);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return ItemRegisterer.RegistrationHandler.CRYSTAL;
    }

    @Override
    public int quantityDropped(Random rand){
        return 1;

        // Use the code below for setting a rand amount from min to max of item dropped
        //int max = 2;
        //int min = 1;
        //return rand.nextInt(max) + min;
    }
}
