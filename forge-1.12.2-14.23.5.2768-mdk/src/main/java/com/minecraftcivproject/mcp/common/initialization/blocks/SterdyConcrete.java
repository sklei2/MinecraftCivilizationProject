package com.minecraftcivproject.mcp.common.initialization.blocks;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.Blueprint;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import registry.BlueprintRegistry;

import java.util.logging.Logger;

public class SterdyConcrete extends BlockBase{

    public SterdyConcrete(){
        super("my_block",
                Material.IRON,
                CreativeTabs.BUILDING_BLOCKS,
                5F,
                15F,
                "pickaxe",
                1);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockAdded(worldIn, pos, state);
    }
}
