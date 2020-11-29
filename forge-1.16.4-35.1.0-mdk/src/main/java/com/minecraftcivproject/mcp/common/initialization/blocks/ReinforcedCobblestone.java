package com.minecraftcivproject.mcp.common.initialization.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ReinforcedCobblestone extends BlockBase{

    public ReinforcedCobblestone(){
        super("reinforced_cobblestone",
                Material.ROCK,
                CreativeTabs.BUILDING_BLOCKS,
                12F,
                17F,
                "pickaxe",
                2);
    }
}
