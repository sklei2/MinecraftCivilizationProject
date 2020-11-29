package com.minecraftcivproject.mcp.common.initialization.register;

import com.minecraftcivproject.mcp.MinecraftCivProject;
import com.minecraftcivproject.mcp.common.initialization.items.*;
import com.minecraftcivproject.mcp.utils.IHasModel;
import com.minecraftcivproject.mcp.utils.RegistryUtil;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemRegisterer {

    /**
     *  Materials
     */
    public static final Item.ToolMaterial MATERIAL_CRYSTAL = EnumHelper.addToolMaterial("crystal", 4, 3000, 10f, 5.0f, 8);


    /**
     *  Tools
     */
    public static final CustomSword CRYSTAL_SWORD = new CustomSword ("crystal_sword", MATERIAL_CRYSTAL);


    /**
     *  Item Registration
     */
    @Mod.EventBusSubscriber(modid = MinecraftCivProject.MODID)
    public static class RegistrationHandler {
        //public static final Set<Item> ITEMS = new HashSet<>();
        public static final List<Item> ITEMS = new ArrayList<Item>();

        // Item Repository
        public static final Item CRYSTAL = new Crystal();
        public static final Item CEMENT = new Cement();
        public static final Item DELETE_OMATIC = new DeleteOMatic();
        public static final Item LV_TOKEN = new LVToken();


        /**
         * Register this mod's {@link Item}s. -> also registers the registry names of the items: THROWS AN ERROR BECAUSE THE NAME OF THE ITEM ALREADY EXIST IN THE ITEM'S CLASS SUPER CONSTRUCTOR - SHOULD THIS BE REMOVED FROM ITS CONSTRUCTOR???
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
//            event.getRegistry().registerAll(ITEMS.toArray(new Item[0]));

            final Item[] items = {
                    CRYSTAL,
                    CEMENT,
                    CRYSTAL_SWORD,
                    DELETE_OMATIC,
                    LV_TOKEN,
                    //RegistryUtil.setItemName(CRYSTAL, "crystal"),  // This is how Choonster sets his item/block registry names in his forge 1.12.2 branch
                    //RegistryUtil.setItemName(new Cement(), "cement")
            };

            // Create forge registry
            final IForgeRegistry<Item> registry = event.getRegistry();

            // Register items
            for (final Item item : items) {
                registry.registerAll(item);
                ITEMS.add(item);
            }
        }


        @SubscribeEvent // This (last) part did the trick for textures/item model rendering!!! --> now it does not work... (10/11/20)
        public static void registerModels(final ModelRegistryEvent event){
            final Item[] items = {
                    CRYSTAL,
                    CEMENT,
                    CRYSTAL_SWORD,
                    DELETE_OMATIC,
                    LV_TOKEN
            };

            for(Item item : items) {
                if (item instanceof IHasModel) {
                    ((IHasModel)item).registerModels();
                }
            }
        }

    }
}
