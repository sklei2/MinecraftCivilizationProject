package com.minecraftcivproject.mcp.common.initialization.blocks;

import com.minecraftcivproject.mcp.utils.Delay;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.logging.Logger;

public class BlueprintBlock extends BlockBase {


    private static Logger logger = Logger.getLogger("BlueprintBlock");
    private final int xExtent;
    private final int zExtent;


    public BlueprintBlock(){
        super("blueprint_block",
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
        Delay.addDelay(1000);

        for(int x = 0; x <= xExtent; x++) {
            for(int z = 0; z <= zExtent; z++) {
                worldIn.setBlockState(pos.add(x, 0, z), Blocks.SEA_LANTERN.getDefaultState());
            }
        }
    }
}
