package com.minecraftcivproject.mcp.common.initialization.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBase extends Block {
    public BlockBase(String name, Material mat, CreativeTabs creativeTabs, float hardness, float resistance, String tool, int harvest) {
        this(name, mat, creativeTabs, hardness, resistance);
        setHarvestLevel(tool, harvest);
    }

    public BlockBase(String name, Material mat, CreativeTabs creativeTabs, float hardness, float resistance, float light){
        this(name, mat, creativeTabs, hardness, resistance);
        setLightLevel(light);
    }

    public BlockBase(String name, Material mat, CreativeTabs creativeTabs, float hardness, float resistance){
        super(mat);
        setUnlocalizedName(name);
        setRegistryName(name);
        setHardness(hardness);
        setResistance(resistance);
        setCreativeTab(creativeTabs);
    }
}
