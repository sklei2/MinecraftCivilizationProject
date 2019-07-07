package com.minecraftcivproject.mcp.common.initialization.blocks;

//import com.minecraftcivproject.mcp.server.managers.tribe.TribeManager;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.Blueprint;
import com.minecraftcivproject.mcp.server.managers.tribe.TribeManager;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import registry.BlueprintRegistry;
import registry.TribeRegistry;

import java.util.logging.Logger;

//import registry.TribeRegistry;        // Should we associate a specific building with a tribe or just a town?

public class StraightWallBlock1 extends BlockBase{

    private static Logger logger = Logger.getLogger("StraightWallBlock1");

    public StraightWallBlock1(){
        super("straight_wall_block_1",
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

        logger.info("A straight wall has spawned.");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Blueprint forgeBlueprint = BlueprintRegistry.getBlueprint("straight_wall_1");
        forgeBlueprint.apply(worldIn, pos);
    }
}
