package com.minecraftcivproject.mcp.common.initialization.register;


import com.google.common.base.Preconditions;
import com.minecraftcivproject.mcp.MinecraftCivProject;
import com.minecraftcivproject.mcp.common.initialization.blocks.*;
import com.minecraftcivproject.mcp.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;


public class BlockRegisterer {


    @Mod.EventBusSubscriber(modid = MinecraftCivProject.MODID)
    public static class RegistrationHandler {

        // Block Repository
        public static final Block TRIBE_BLOCK = new TribeBlock();
        public static final Block MY_BLOCK = new MyBlock();
        public static final Block VILLAGER_BLOCK = new VillagerBlock();
        public static final Block LEXICON = new Lexicon();
        public static final Block CRYSTAL_ORE = new CrystalOre();
        public static final Block STURDY_CONCRETE = new SturdyConcrete();
        public static final Block STRAIGHT_WALL_BLOCK_1 = new StraightWallBlock1();

        // Creates Item Blocks (different than an Item)
        public static final ItemBlock[] ITEM_BLOCKS = {
                new ItemBlock(TRIBE_BLOCK),
                new ItemBlock(MY_BLOCK),
                new ItemBlock(VILLAGER_BLOCK),
                new ItemBlock(LEXICON),
                new ItemBlock(CRYSTAL_ORE),
                new ItemBlock(STURDY_CONCRETE),
                new ItemBlock(STRAIGHT_WALL_BLOCK_1)
        };

        // Creates a list of blocks to get registered on startup
        public static final Block[] BLOCKS = {
                TRIBE_BLOCK,
                MY_BLOCK,
                VILLAGER_BLOCK,
                LEXICON,
                CRYSTAL_ORE,
                STURDY_CONCRETE,
                STRAIGHT_WALL_BLOCK_1
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
                    new ItemBlock(VILLAGER_BLOCK),
                    new ItemBlock(LEXICON),
                    new ItemBlock(CRYSTAL_ORE),
                    new ItemBlock(STURDY_CONCRETE),
                    new ItemBlock(STRAIGHT_WALL_BLOCK_1)
            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final ItemBlock item : ITEM_BLOCKS) {
                final Block block = item.getBlock();
                final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
                registry.register(item.setRegistryName(registryName));
            }
        }

       // @SubscribeEvent
       // public static void registerRenders(ModelRegistryEvent event){
       //     for(ItemBlock itemBlock : ITEM_BLOCKS){
       //         Item item = Item.getItemFromBlock(itemBlock.getBlock());
       //         ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
       //     }
       // }
        // This might be trying to do the same this as the event below but messing it up with the CustomModelResourceLocation <- May not be the case, I forgot to add in the Item Model of Lexicon


        @SubscribeEvent
        public static void registerModels(final ModelRegistryEvent event){
            final Block[] blocks = {
                    TRIBE_BLOCK,
                    MY_BLOCK,
                    VILLAGER_BLOCK,
                    LEXICON,
                    CRYSTAL_ORE,
                    STURDY_CONCRETE,
                    STRAIGHT_WALL_BLOCK_1
            };

            for(Block block : blocks) {
                if (block instanceof IHasModel) {
                    ((IHasModel)block).registerModels();
                }
            }
        }
    }
}
