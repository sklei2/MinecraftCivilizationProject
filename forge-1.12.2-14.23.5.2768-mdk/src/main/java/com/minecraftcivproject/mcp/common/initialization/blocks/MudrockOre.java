package com.minecraftcivproject.mcp.common.initialization.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import java.util.Random;

public class MudrockOre extends BlockBase{

    public MudrockOre(){
        super("mudrock_ore",
                Material.ROCK,
                CreativeTabs.BUILDING_BLOCKS,
                5F,
                15F,
                "pickaxe",
                2);
    }

}
