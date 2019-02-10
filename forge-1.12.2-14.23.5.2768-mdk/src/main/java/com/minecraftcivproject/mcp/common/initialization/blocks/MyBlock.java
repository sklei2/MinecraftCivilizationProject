package com.minecraftcivproject.mcp.common.initialization.blocks;

import com.minecraftcivproject.mcp.server.managers.tribe.TribeManager;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import registry.TribeRegistry;

import java.util.logging.Logger;

public class MyBlock extends BlockBase{

    private static Logger logger = Logger.getLogger("MyBlock");

    public MyBlock(){
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

        logger.info("oh hey, I'm my block!");
        //TribeRegistry.addTribe("Sean", new TribeManager());


    }
}
