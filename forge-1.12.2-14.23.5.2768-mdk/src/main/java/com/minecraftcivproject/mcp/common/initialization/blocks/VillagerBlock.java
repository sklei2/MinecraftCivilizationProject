package com.minecraftcivproject.mcp.common.initialization.blocks;

import com.minecraftcivproject.mcp.common.entity.LoyalVillager;
import com.minecraftcivproject.mcp.utils.SpawningUtils;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.logging.Logger;

public class VillagerBlock extends BlockBase{

    private static Logger logger = Logger.getLogger("VillagerBlock");

    public VillagerBlock(){
        super("villager_block",
                Material.IRON,
                CreativeTabs.BUILDING_BLOCKS,
                5F,
                20F,
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

        logger.info("A Loyal Villager has spawned!");
        //TribeRegistry.addTribe("Sean", new TribeManager());

        LoyalVillager villager = new LoyalVillager(worldIn);
        //EntityVillager villager = new EntityVillager(world);       // For testing purposes only

        SpawningUtils.spawn(villager, worldIn, pos);
    }
}
