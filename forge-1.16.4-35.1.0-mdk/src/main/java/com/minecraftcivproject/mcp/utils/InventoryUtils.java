package com.minecraftcivproject.mcp.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

public class InventoryUtils {

    public static int pickupItem(World world, Entity entity, InventoryBasic inventory, Item itemOfInterest) {  // Might want to change this to a different return type later
        int itemCnt = 0;
        AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().grow(2.0D, 1.0D, 2.0D);  // Creates an axis-aligned bounding box around the entity
        List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity, axisalignedbb);

        for(int i = 0; i < list.size(); ++i) {
            Entity entityOnGround = list.get(i);
            if (entityOnGround instanceof EntityItem && ((EntityItem) entityOnGround).getItem().getItem().equals(itemOfInterest)) {
                EntityItem entityItem = (EntityItem)entityOnGround;  // Must cast entity into an EntityItem even if it's already an EntityItem to be able to call EntityItem methods on it!
                ItemStack itemStack = entityItem.getItem();

                inventory.addItem(itemStack);
                entityItem.setDead();  // Destroys the item block on the ground

                itemCnt += itemStack.getCount();
            }
        }

        return itemCnt;
    }
}
