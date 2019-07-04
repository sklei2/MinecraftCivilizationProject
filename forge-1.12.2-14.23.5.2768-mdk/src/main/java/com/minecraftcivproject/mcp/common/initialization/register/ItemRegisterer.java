package com.minecraftcivproject.mcp.common.initialization.register;

import com.minecraftcivproject.mcp.MinecraftCivProject;
import com.minecraftcivproject.mcp.common.initialization.items.Cement;
import com.minecraftcivproject.mcp.common.initialization.items.Crystal;
import com.minecraftcivproject.mcp.utils.IHasModel;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

public class ItemRegisterer {

    /**
     *  Materials
     */
    public static final Item.ToolMaterial CRYSTAL = EnumHelper.addToolMaterial("crystal", 4, 2048, 12f, 5.0f, 30);


    /**
     *  Item Registration
     */
    @Mod.EventBusSubscriber(modid = MinecraftCivProject.MODID)
    public static class RegistrationHandler {
        public static final Set<Item> ITEMS = new HashSet<>();

        // Item Repository
        public static final Item CRYSTAL_ITEM = new Crystal();
        public static final Item CEMENT = new Cement();

        /**
         * Register this mod's {@link Item}s. -> also registers the registry names of the items: THROWS AN ERROR BECAUSE THE NAME OF THE ITEM ALREADY EXIST IN THE ITEM'S CLASS SUPER CONSTRUCTOR - SHOULD THIS BE REMOVED FROM ITS CONSTRUCTOR???
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            final Item[] items = {
                    CRYSTAL_ITEM,
                    CEMENT
                    //setItemName(new Crystal(), "crystal"),
                    //setItemName(new Cement(), "cement")
            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final Item item : items) {
                registry.register(item);
                ITEMS.add(item);
            }
        }

        @SubscribeEvent // This (last) part did the trick for textures/item model rendering!!!
        public static void registerModels(final ModelRegistryEvent event){
            final Item[] items = {
                    CRYSTAL_ITEM,
                    CEMENT
            };

            for(Item item : items) {
                if (item instanceof IHasModel) {
                    ((IHasModel)item).registerModels();
                }
            }
        }

    }
}
