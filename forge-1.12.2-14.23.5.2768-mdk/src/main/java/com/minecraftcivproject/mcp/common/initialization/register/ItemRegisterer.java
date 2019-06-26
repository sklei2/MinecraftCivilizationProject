package com.minecraftcivproject.mcp.common.initialization.register;

import com.minecraftcivproject.mcp.MinecraftCivProject;
import com.minecraftcivproject.mcp.common.initialization.items.Cement;
import com.minecraftcivproject.mcp.common.initialization.items.HonedDiamond;
import com.minecraftcivproject.mcp.utils.IHasModel;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

public class ItemRegisterer {


    @Mod.EventBusSubscriber(modid = MinecraftCivProject.MODID)
    public static class RegistrationHandler {
        public static final Set<Item> ITEMS = new HashSet<>();

        // Item Repository
        public static final Item HONED_DIAMOND = new HonedDiamond();
        public static final Item CEMENT = new Cement();

        /**
         * Register this mod's {@link Item}s. -> registers these blocks.items on startup
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            final Item[] items = {
                    HONED_DIAMOND,
                    CEMENT
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
                    HONED_DIAMOND,
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
