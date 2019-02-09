package com.minecraftcivproject.mcp.common.initialization.blocks;

import com.minecraftcivproject.mcp.server.managers.tribe.TribeManager;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import registry.TribeRegistry;

import java.util.logging.Logger;

public class TribeBlock extends BlockBase{

    private static Logger logger = Logger.getLogger("TribeBlock");

    public TribeBlock(){
        super("tribe_block",
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

        logger.info("oh hey, I'm a tribe block!");
        TribeRegistry.addTribe("Sean", new TribeManager());


    }
}
