package com.minecraftcivproject.mcp.common.initialization.blocks;

import com.minecraftcivproject.mcp.TickWatcher;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.Blueprint;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBlueprint;
import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;
import com.minecraftcivproject.mcp.server.managers.tribe.Tribe;
import com.minecraftcivproject.mcp.server.managers.tribe.TribeManager;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import registry.BlueprintRegistry;
import registry.TownBlueprintRegistry;
import registry.TribeRegistry;
import ui.tribe.general.TribeQueuesUi;
import ui.tribe.general.TribeUi;

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
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {

        super.onBlockAdded(world, pos, state);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        logger.info("oh hey, I'm a tribe block!");
        int dimension = 29;

        spawnBedrockFloor(world, pos, dimension);

        Blueprint nexus = BlueprintRegistry.getBlueprint("town_nexus");
        nexus.apply(world, pos.add(-1,0,-1));  // -1's center the nexus with the placed position

        TownBlueprint townBlueprint = TownBlueprintRegistry.getTownBlueprint("town");

        QueueManager queueManager = new QueueManager();

        TribeQueuesUi tribeQueuesUi = new TribeQueuesUi(queueManager);

        TribeManager tribeManager = new TribeManager(townBlueprint, world, pos, queueManager);  // new TribeManager -> new GoalManager -> new VillagerManager -> new VillagerPoolManager (don't know why this is here) -> new LV... we should really spawn 2 eventually
        String tribeName = "Sean's Pawns";
        Tribe tribe = new Tribe(tribeName, tribeManager, new TribeUi(tribeName, tribeQueuesUi), world);

        new TickWatcher(tribeManager::onTick);

        TribeRegistry.addTribe(tribe.getTribeName(), tribe);
    }

    private void spawnBedrockFloor(World world, BlockPos center, int extent) {
        // Extent MUST be odd (to have a center block)
        int halfExt = extent/2;
        for (int y = -7; y >= -9; --y) {
            for (int x = -halfExt; x <= halfExt; ++x) {
                for (int z = -halfExt; z <= halfExt; ++z) {
                    world.setBlockState(center.add(x, y, z), Blocks.BEDROCK.getDefaultState());
//                    if (Math.abs(x) == halfExt || Math.abs(z) == halfExt) {
//                        world.setBlockState(center.add(x, -2, z), Blocks.BEDROCK.getDefaultState());
//                        world.setBlockState(center.add(x, -1, z), Blocks.BEDROCK.getDefaultState());  // Assumes the tribe block was placed on top of ground level
//                    }
                }
            }
        }
    }

}
