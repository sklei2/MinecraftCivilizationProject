package com.minecraftcivproject.mcp.common.initialization.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
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
    private int posCounter;

    public DeleteOMatic() {
        super("deleteomatic",
                CreativeTabs.MISC,
                64);
        this.posCounter = 0;
    }

//    @Override
//    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
//        return new ActionResult(EnumActionResult.PASS, p_onItemRightClick_2_.getHeldItem(p_onItemRightClick_3_));
//    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float f1, float f2, float f3) {
        System.out.println("DELETE DELETE DELETE");
        ++posCounter;
        if (posCounter == 1) {
            firstPos = pos;
            System.out.println("First position = " + pos);
        } else if (posCounter == 2) {
            secondPos = pos;
            System.out.println("Second position = " + pos);
            //world.destroyBlock()
        }
        return EnumActionResult.PASS;
    }

}
