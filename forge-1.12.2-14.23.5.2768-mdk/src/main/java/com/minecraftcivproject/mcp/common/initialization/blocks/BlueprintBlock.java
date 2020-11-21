package com.minecraftcivproject.mcp.common.initialization.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.logging.Logger;

public class BlueprintBlock extends BlockBase {


    private static Logger logger = Logger.getLogger("TribeBlock");
    private final int xExtent;
    private final int zExtent;


    public BlueprintBlock(){
        super("bluebrint_block",
                Material.IRON,
                CreativeTabs.BUILDING_BLOCKS,
                5F,
                15F,
                "pickaxe",
                1);

        this.xExtent = 10;
        this.zExtent = 10;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockAdded(worldIn, pos, state);

        // A delay is needed for this to actually spawn blocks...
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int x = 0; x <= xExtent; x++) {
            for(int z = 0; z <= zExtent; z++) {
                worldIn.setBlockState(pos.add(x, 0, z), Blocks.SEA_LANTERN.getDefaultState());
            }
        }
    }
}
