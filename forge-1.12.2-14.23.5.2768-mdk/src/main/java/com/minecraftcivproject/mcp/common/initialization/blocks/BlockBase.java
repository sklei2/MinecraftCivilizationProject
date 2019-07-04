package com.minecraftcivproject.mcp.common.initialization.blocks;

import com.minecraftcivproject.mcp.MinecraftCivProject;
import com.minecraftcivproject.mcp.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BlockBase extends Block implements IHasModel {
    public BlockBase(String name, Material mat, CreativeTabs creativeTabs, float hardness, float resistance, String tool, int harvest) {
        this(name, mat, creativeTabs, hardness, resistance);
        setHarvestLevel(tool, harvest);                             // It seems like TribeBlock uses this constructor. Why have the two below?
    }

    public BlockBase(String name, Material mat, CreativeTabs creativeTabs, float hardness, float resistance, float light) {
        this(name, mat, creativeTabs, hardness, resistance);
        setLightLevel(light);                                       // Why is this constructor in here?? Is this just another constructor that can be used?
    }

    public BlockBase(String name, Material mat, CreativeTabs creativeTabs, float hardness, float resistance) {
        super(mat);
        setUnlocalizedName(name);
        setRegistryName(name);
        setHardness(hardness);
        setResistance(resistance);
        setCreativeTab(creativeTabs);               // What would this be called? It doesn't seem like a constructor, more like the thing that assigns values for the constructor to use to construct an object
    }

    @Override
    public void registerModels() {
        MinecraftCivProject.PROXY.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
