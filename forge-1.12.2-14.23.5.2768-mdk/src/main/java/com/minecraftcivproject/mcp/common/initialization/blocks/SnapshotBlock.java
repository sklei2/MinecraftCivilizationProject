package com.minecraftcivproject.mcp.common.initialization.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SnapshotBlock extends BlockBase {

    private static Logger logger = Logger.getLogger("SnapshotBlock");
    private final int xExtent;
    private final int yExtent;
    private final int zExtent;
    private Map<BlockPos,Block> blueprint = new HashMap<>();


    public SnapshotBlock() {
        super("snapshot_block",
                Material.IRON,
                CreativeTabs.BUILDING_BLOCKS,
                5F,
                15F,
                "pickaxe",
                1);

        this.xExtent = 10;
        this.zExtent = 10;
        this.yExtent = 20;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockAdded(worldIn, pos, state);

        for (int y = 0; y <= yExtent; y++) {
            for (int x = 1; x <= xExtent; x++) {
                for (int z = 1; z <= zExtent; z++) {
                    BlockPos newPos = pos.add(x, y, z);
                    Block block = worldIn.getBlockState(newPos).getBlock();
                    blueprint.put(newPos, block);
                }
            }
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float f1, float f2, float f3) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~ Snapshot Block Activate!!! ~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        blueprint.forEach((blockPos, block) -> System.out.println(blockPos + "," + block));
        blueprint.forEach((blockPos, block) -> worldIn.setBlockState(blockPos, block.getDefaultState()));
        return true;
    }
}
