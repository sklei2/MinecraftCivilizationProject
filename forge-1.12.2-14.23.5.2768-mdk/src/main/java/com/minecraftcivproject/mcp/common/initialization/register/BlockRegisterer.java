package com.minecraftcivproject.mcp.common.initialization.register;


import com.google.common.base.Preconditions;
import com.minecraftcivproject.mcp.MinecraftCivProject;
import com.minecraftcivproject.mcp.common.initialization.blocks.TribeBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

public class BlockRegisterer {


    @Mod.EventBusSubscriber(modid = MinecraftCivProject.MODID)
    public static class RegistrationHandler {

        private static final Block TRIBE_BLOCK = new TribeBlock();


        public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();


        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> registry = event.getRegistry();

            final Block[] blocks = {
                    TRIBE_BLOCK
            };

            registry.registerAll(blocks);
        }


        @SubscribeEvent
        public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
            final ItemBlock[] items = {
                    new ItemBlock(TRIBE_BLOCK)
            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final ItemBlock item : items) {
                final Block block = item.getBlock();
                final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
                registry.register(item.setRegistryName(registryName));
                ITEM_BLOCKS.add(item);
            }
        }
    }
}
