package com.minecraftcivproject.mcp.common.initialization.blocks;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBlueprint;
import com.minecraftcivproject.mcp.server.managers.tribe.Tribe;
import com.minecraftcivproject.mcp.server.managers.tribe.TribeManager;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import registry.TownBlueprintRegistry;
import registry.TribeRegistry;
import ui.tribe.general.TribeQueuesUi;
import ui.tribe.general.TribeUi;
import ui.tribe.queuedisplay.QueueListener;

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

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        logger.info("oh hey, I'm a tribe block!");
        TownBlueprint townBlueprint = TownBlueprintRegistry.getTownBlueprint("test_town");

        TribeQueuesUi tribeQueuesUi = new TribeQueuesUi();
        QueueListener queueListener = new QueueListener(tribeQueuesUi);
        TribeManager tribeManager = new TribeManager(townBlueprint, worldIn, pos, queueListener);
        Tribe tribe = new Tribe("Sean", tribeManager, new TribeUi("Sean", tribeQueuesUi), worldIn);

        TribeRegistry.addTribe(tribe.getTribeName(), tribe);
    }
}
