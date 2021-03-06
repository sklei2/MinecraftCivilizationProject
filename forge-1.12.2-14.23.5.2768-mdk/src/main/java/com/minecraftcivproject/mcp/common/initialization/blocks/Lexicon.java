package com.minecraftcivproject.mcp.common.initialization.blocks;

import com.minecraftcivproject.mcp.MinecraftCivProject;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;

public class Lexicon extends BlockBase {

    public Lexicon(){
        super("lexicon",
                Material.WOOD,
                CreativeTabs.DECORATIONS,
                5F,
                15F,
                "pickaxe",
                0);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockAdded(worldIn, pos, state);
    }  // This might not be needed if we don't want anything to happen when this block is placed

    /**
     * Called when the block is right clicked by a player.
     */

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            //playerIn.openGui();
            playerIn.addStat(StatList.CRAFTING_TABLE_INTERACTION);
            return true;
        }
    }

    //public static class ExtendedInterfaceCraftingTable implements IInteractionObject
    //{
        //private final World world;
        //private final BlockPos position;

        //public InterfaceCraftingTable(World worldIn, BlockPos pos)
        //{
            //this.world = worldIn;
            //this.position = pos;
        //}

        /**
         * Get the name of this object. For players this returns their username
         */
        //public String getName()
        //{
           // return "crafting_table";
        //}

        /**
         * Returns true if this thing is named
         */
        //public boolean hasCustomName()
        //{
          //  return false;
        //}

        /**
         * Get the formatted ChatComponent that will be used for the sender's username in chat
         */
        //public ITextComponent getDisplayName()
        //{
            //return new TextComponentTranslation(Blocks.CRAFTING_TABLE.getUnlocalizedName() + ".name", new Object[0]);
        //}

        //public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
        //{
            //return new ContainerWorkbench(playerInventory, this.world, this.position);
        //}

        //public String getGuiID()
        //{
            //return "minecraft:lexicon";
        //}
    //}

}
