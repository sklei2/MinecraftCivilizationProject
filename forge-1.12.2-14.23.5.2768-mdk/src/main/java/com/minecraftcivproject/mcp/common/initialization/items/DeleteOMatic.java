package com.minecraftcivproject.mcp.common.initialization.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DeleteOMatic extends ItemBase {

    private BlockPos firstPos;
    private BlockPos secondPos;
    private int posCounter = 0;

    public DeleteOMatic() {
        super("deleteomatic",
                CreativeTabs.MISC,
                64);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float f1, float f2, float f3) {
        // Only increment on the server side (1. Is catch-all for both single and multi-player 2. Will do twice if not)
        ++posCounter;
        System.out.println("posCounter = " + posCounter);
        if (posCounter == 2) {
            firstPos = pos;
            System.out.println("First position = " + pos);
        } else if (posCounter == 4) {
            secondPos = pos;
            System.out.println("Second position = " + pos);
            System.out.println("DELETE DELETE DELETE");
            deleteBlocks(world);
            posCounter = 0;
        }

        return EnumActionResult.PASS;
    }

    private void deleteBlocks(World world) {
        int xExtent = secondPos.getX() - firstPos.getX();
        int yExtent = secondPos.getY() - firstPos.getY();
        int zExtent = secondPos.getZ() - firstPos.getZ();
        for (int y = 0; y <= Math.abs(yExtent); ++y) {
            for (int x = 0; x <= Math.abs(xExtent); ++x) {
                for (int z = 0; z <= Math.abs(zExtent); ++z) {
                    world.destroyBlock(firstPos.add((int)Math.signum(xExtent)*x,(int)Math.signum(yExtent)*y,(int)Math.signum(zExtent)*z),false);
                }
            }
        }
    }

}
