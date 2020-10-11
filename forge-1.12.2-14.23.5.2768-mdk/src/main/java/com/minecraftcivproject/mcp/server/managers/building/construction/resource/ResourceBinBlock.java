package com.minecraftcivproject.mcp.server.managers.building.construction.resource;

import com.minecraftcivproject.mcp.MinecraftCivProject;
import com.minecraftcivproject.mcp.common.initialization.blocks.BlockTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
    private String id;


    public ResourceBinBlock(){
        super(Material.WOOD, false);
        setRegistryName(MinecraftCivProject.MODID, name);
        setUnlocalizedName(name);
        this.id = UUID.randomUUID().toString();
    }

    public String getId(){
        return this.id;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new ResourceBinTileEntity(id, new ResourceBinInventory(id));
    }

    @Override
    public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
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
}
