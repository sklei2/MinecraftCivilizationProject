package com.minecraftcivproject.mcp.utils;

import com.google.common.base.Preconditions;
import com.minecraftcivproject.mcp.MinecraftCivProject;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryManager;

/**
 * Utility methods for Forge registries.
 *
 */
public class RegistryUtil {

    /**
     * Get an entry from the provided registry.
     *
     * @param registry The registry
     * @param modid    The mod ID of the entry
     * @param name     The name of the entry
     * @param <T>      The registry type
     * @return The registry entry
     * @throws NullPointerException When the entry doesn't exist
     */
    public static <T extends IForgeRegistryEntry<T>> T getRegistryEntry(final IForgeRegistry<T> registry, final String modid, final String name) {
        final ResourceLocation key = new ResourceLocation(modid, name);
        final T registryEntry = registry.getValue(key);
        return Preconditions.checkNotNull(registryEntry, "%s doesn't exist in registry %s", key, RegistryManager.ACTIVE.getName(registry));
    }

    /**
     * Set the registry name of {@code block} to {@code blockName} and the translation key to the full registry name.
     *
     * @param block     The block
     * @param blockName The block's name
     * @param <BLOCK>   The block type
     * @return The block
     */
    public static <BLOCK extends Block> BLOCK setBlockName(final BLOCK block, final String blockName) {
        block.setRegistryName(MinecraftCivProject.MODID, blockName);
        final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName());
        //block.setTranslationKey(registryName.toString());
        return block;
    }

    /**
     * Set the registry name of {@code item} to {@code itemName} and the translation key to the full registry name.
     *
     * @param item     The item
     * @param itemName The item's name
     */
    public static <ITEM extends Item> ITEM setItemName(final ITEM item, final String itemName) {
        item.setRegistryName(MinecraftCivProject.MODID, itemName);
        final ResourceLocation registryName = Preconditions.checkNotNull(item.getRegistryName());
        //item.setTranslationKey(registryName.toString());
        return item;
    }

}
