package com.minecraftcivproject.mcp.common.initialization.blocks;

//import com.minecraftcivproject.mcp.server.managers.tribe.TribeManager;      // This may be causing the issue, as it is unused by MyBlock but used by TribeBlock
import com.minecraftcivproject.mcp.common.initialization.register.BlockRegisterer;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.Blueprint;
import com.minecraftcivproject.mcp.server.managers.building.construction.resource.ResourceBinBlock;
import com.minecraftcivproject.mcp.utils.BlockUtils;
import net.minecraft.block.BlockHay;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
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

        System.out.println("New ResourceBinBlock's default state is "+new ResourceBinBlock().getDefaultState());
        worldIn.setBlockState(pos.add(1, 0, 1), new ResourceBinBlock().getDefaultState());
        System.out.println("Static ResourceBinBlock's default state is "+BlockRegisterer.RegistrationHandler.RESOURCE_BIN_BLOCK.getDefaultState());
        worldIn.setBlockState(pos.add(2, 0, 2), BlockRegisterer.RegistrationHandler.RESOURCE_BIN_BLOCK.getDefaultState());
        System.out.println("Static Hay Block's default state is "+Blocks.HAY_BLOCK.getDefaultState());
        worldIn.setBlockState(pos.add(-1, 0, 1), Blocks.HAY_BLOCK.getDefaultState());
        System.out.println("New Hay Block's default state is "+new BlockHay().getDefaultState());
        worldIn.setBlockState(pos.add(-2, 0, 2), new BlockHay().getDefaultState());
        System.out.println("New Crystal Ore's default state is "+new CrystalOre().getDefaultState());
        worldIn.setBlockState(pos.add(1, 0, -1), new CrystalOre().getDefaultState());
        System.out.println("Static Crystal Ore's default state is "+ BlockRegisterer.RegistrationHandler.CRYSTAL_ORE.getDefaultState());
        worldIn.setBlockState(pos.add(2, 0, -2), BlockRegisterer.RegistrationHandler.CRYSTAL_ORE.getDefaultState());

//        logger.info("oh hey, a tree farm!");
//        //TribeRegistry.addTribe("Sean", new TribeManager());
//
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        Blueprint forgeBlueprint = BlueprintRegistry.getBlueprint("tree_farm_lvl1");
//        forgeBlueprint.apply(worldIn, pos);
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer player) {
        //worldIn.playSound(player, pos, SoundEvents.BLOCK_NOTE_HARP, SoundCategory.RECORDS, 3.0F, 50);
        worldIn.setBlockState(pos.add(1, 0, 1), Blocks.HAY_BLOCK.getDefaultState());
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float f1, float f2, float f3) {
        worldIn.setBlockState(pos.add(1, 0, 1), Blocks.HAY_BLOCK.getDefaultState());
        return true;
    }
}
