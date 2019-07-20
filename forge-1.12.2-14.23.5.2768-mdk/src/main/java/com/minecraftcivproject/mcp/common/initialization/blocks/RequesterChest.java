package com.minecraftcivproject.mcp.common.initialization.blocks;

import com.minecraftcivproject.mcp.MinecraftCivProject;
import com.minecraftcivproject.mcp.common.entity.LoyalVillager;
import com.minecraftcivproject.mcp.utils.SpawningUtils;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RequesterChest extends BlockChest {

    protected RequesterChest(Type chestTypeIn) {
        super(chestTypeIn);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockAdded(worldIn, pos, state);

        World world = MinecraftCivProject.getWorld();
        LoyalVillager villager = new LoyalVillager(world);

        SpawningUtils.spawn(villager, pos);
    }
}
