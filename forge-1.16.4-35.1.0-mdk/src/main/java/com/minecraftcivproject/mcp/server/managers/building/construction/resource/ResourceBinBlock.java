package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import com.minecraftcivproject.mcp.MinecraftCivProject;
import com.minecraftcivproject.mcp.common.initialization.blocks.BlockTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;


public class ResourceBinBlock extends BlockTileEntity<ResourceBinTileEntity> {

    private static final String name = "resource_bin";
    private ResourceBinTileEntity resourceBinTileEntity;


    public ResourceBinBlock() {
        super(Material.WOOD, false);
        setRegistryName(MinecraftCivProject.MODID, name);
        setRegistryName(name);
    }

    @Override
    public TileEntity createTileEntity(World world, BlockState state) {
        String id = UUID.randomUUID().toString();
        return new ResourceBinTileEntity(id, new ResourceBinInventory(id));
    }

    @Override
    public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final BlockState state, final LivingEntity placer, final ItemStack stack) {
        if (stack.hasDisplayName()) {
            final ResourceBinTileEntity tileEntity = getTileEntity(worldIn, pos);
            if (tileEntity != null) {
                tileEntity.add(stack.getItem(), stack.getCount());
            }
        }
    }

    @Override
    public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing side, final float hitX, final float hitY, final float hitZ) {
        ILockableContainer ilockablecontainer = this.getContainer(worldIn, pos);
        playerIn.displayGUIChest(ilockablecontainer);
        return true;
    }

    @Nullable
    private ILockableContainer getContainer(World worldIn, BlockPos pos) {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (!(tileentity instanceof ResourceBinTileEntity)) {
            return null;
        } else {
            return (ResourceBinTileEntity) tileentity;
        }
    }

    @Override
    public void registerModels() {
        MinecraftCivProject.PROXY.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
