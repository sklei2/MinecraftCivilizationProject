package com.minecraftcivproject.mcp.common.initialization.blocks;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.Blueprint;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import registry.BlueprintRegistry;

import java.util.logging.Logger;

public class SturdyConcrete extends BlockBase{

    public SturdyConcrete(){
        super("sturdy_concrete",
                Material.ROCK,
                CreativeTabs.BUILDING_BLOCKS,
                17F,
                22F,
                "pickaxe",
                3);
    }
}
