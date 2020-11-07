package com.minecraftcivproject.mcp.common.initialization.register;


import com.google.common.base.Preconditions;
import com.minecraftcivproject.mcp.MinecraftCivProject;
import com.minecraftcivproject.mcp.common.initialization.blocks.*;
import com.minecraftcivproject.mcp.server.managers.building.construction.resource.ResourceBinBlock;
import com.minecraftcivproject.mcp.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;


public class BlockRegisterer {


    // WHY IN THE FUCK IS THERE A CLASS INSIDE OF A CLASS...... THIS IS WHAT HAPPENS WHEN YOU TRUST SOME RANDOM, UNDOCUMENTED GIT REPO AS YOUR MOD FRAMEWORK......... FUCK CHOONSTER
    // I LITERALLY GOT MORE SHIT DONE WATCHING A 12 YEAR OLD'S TUTORIAL THAN TRYING TO FIGURE THIS SHIT OUT
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
        public static final Block RESOURCE_BIN_BLOCK = new ResourceBinBlock();
        public static final Block MUDROCK_ORE = new MudrockOre();
        public static final Block REINFORCED_COBBLESTONE = new ReinforcedCobblestone();

        // Creates Item Blocks (different than an Item)
        public static final ItemBlock[] ITEM_BLOCKS = {
                new ItemBlock(TRIBE_BLOCK),
                new ItemBlock(MY_BLOCK),
                new ItemBlock(VILLAGER_BLOCK),
                new ItemBlock(LEXICON),
                new ItemBlock(CRYSTAL_ORE),
                new ItemBlock(STURDY_CONCRETE),
                new ItemBlock(RESOURCE_BIN_BLOCK),
                new ItemBlock(STRAIGHT_WALL_BLOCK_1),
                new ItemBlock(MUDROCK_ORE),
                new ItemBlock(REINFORCED_COBBLESTONE)
        };

        // Creates a list of blocks (BLOCKS) to get registered on startup
        public static final Block[] BLOCKS = {
                TRIBE_BLOCK,
                MY_BLOCK,
                VILLAGER_BLOCK,
                LEXICON,
                CRYSTAL_ORE,
                STURDY_CONCRETE,
                MUDROCK_ORE,
                REINFORCED_COBBLESTONE,
                STRAIGHT_WALL_BLOCK_1,
                RESOURCE_BIN_BLOCK
        };


        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> registry = event.getRegistry();

            for (Block b : BLOCKS){
                b.setCreativeTab(CreativeTabs.MISC);
            }

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
                    new ItemBlock(MUDROCK_ORE),
                    new ItemBlock(REINFORCED_COBBLESTONE),
                    new ItemBlock(STRAIGHT_WALL_BLOCK_1),
                    new ItemBlock(RESOURCE_BIN_BLOCK)
            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final ItemBlock item : ITEM_BLOCKS) {
                final Block block = item.getBlock();

                final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
                /* How do blocks have the getRegistryName method as an attribute??? ->
                        public class Block extends Impl<Block>
                        public static class Impl<T extends IForgeRegistryEntry<T>> implements IForgeRegistryEntry<T>
                        IForgeRegistryEntry<V>
                 */

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
        // This might be trying to do the same as the event below but messing it up with the CustomModelResourceLocation <- May not be the case, I forgot to add in the Item Model of Lexicon


        @SubscribeEvent
        public static void registerModels(final ModelRegistryEvent event){
            final Block[] blocks = {
                    TRIBE_BLOCK,
                    MY_BLOCK,
                    VILLAGER_BLOCK,
                    LEXICON,
                    CRYSTAL_ORE,
                    STURDY_CONCRETE,
                    MUDROCK_ORE,
                    REINFORCED_COBBLESTONE,
                    STRAIGHT_WALL_BLOCK_1,
                    RESOURCE_BIN_BLOCK
            };

            for(Block block : blocks) {
                if (block instanceof IHasModel) {
                    ((IHasModel)block).registerModels();
                }
            }
        }
    }



    // From TechnoVision's Frabic API tutorial -> THERE IS NO REGISTRY CLASS IN NET.MINECRAFT.UTIL.REGISTRY
//    public static final Block CRYSTAL_ORE = new CrystalOre();
//
//    public static void registerItems(){
//
//    }
}
