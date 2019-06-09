package com.minecraftcivproject.mcp.common.initialization.register;


import com.google.common.base.Preconditions;
import com.minecraftcivproject.mcp.MinecraftCivProject;
import com.minecraftcivproject.mcp.common.initialization.blocks.TribeBlock;
import com.minecraftcivproject.mcp.common.initialization.blocks.MyBlock;
import com.minecraftcivproject.mcp.common.initialization.blocks.VillagerBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;


public class BlockRegisterer {


    @Mod.EventBusSubscriber(modid = MinecraftCivProject.MODID)
    public static class RegistrationHandler {

        // Block Repository
        private static final Block TRIBE_BLOCK = new TribeBlock();
        private static final Block MY_BLOCK = new MyBlock();            // Needed to just alt+Enter to resolve issue?? -> Yes, alt+Enter adds the missing imports to the BlockRegister class
        private static final Block VILLAGER_BLOCK = new VillagerBlock();

        // Creates Item Blocks (different than an Item)
        public static final ItemBlock[] ITEM_BLOCKS = {
            new ItemBlock(TRIBE_BLOCK),
            new ItemBlock(MY_BLOCK),
            new ItemBlock(VILLAGER_BLOCK)
        };

        // Creates a list of blocks to get registered on startup
        public static final Block[] BLOCKS = {
                TRIBE_BLOCK,
                MY_BLOCK,
                VILLAGER_BLOCK
        };


        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> registry = event.getRegistry();

            registry.registerAll(BLOCKS);

            //following what the example mod does
            TileEntityRegisterer.registerTileEntities();
        }


        @SubscribeEvent
        public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
            final ItemBlock[] items = {
                    new ItemBlock(TRIBE_BLOCK),
                    new ItemBlock(MY_BLOCK),
                    new ItemBlock(VILLAGER_BLOCK)
            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final ItemBlock item : ITEM_BLOCKS) {
                final Block block = item.getBlock();
                final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
                registry.register(item.setRegistryName(registryName));
            }
        }

        @SubscribeEvent
        public static void registerRenders(ModelRegistryEvent event){
            for(ItemBlock itemBlock : ITEM_BLOCKS){
                Item item = Item.getItemFromBlock(itemBlock.getBlock());
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
            }
        }
    }
}
