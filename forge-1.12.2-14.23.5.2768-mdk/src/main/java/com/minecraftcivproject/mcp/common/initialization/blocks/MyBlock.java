package com.minecraftcivproject.mcp.common.initialization.blocks;

//import com.minecraftcivproject.mcp.server.managers.tribe.TribeManager;      // This may be causing the issue, as it is unused by MyBlock but used by TribeBlock
import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.Blueprint;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import registry.BlueprintRegistry;
//import registry.TribeRegistry;        // Should we associate a specific building with a tribe or just a town?

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

        logger.info("oh hey, a forge!");
        //TribeRegistry.addTribe("Sean", new TribeManager());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Blueprint forgeBlueprint = BlueprintRegistry.getBlueprint("forge");
        forgeBlueprint.apply(worldIn, pos);
    }
}
