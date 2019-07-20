package com.minecraftcivproject.mcp.common.initialization.register;

import com.minecraftcivproject.mcp.MinecraftCivProject;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableRegisterer {

    /**
     * Register this mod's {@link LootTable}s.
     */
    public static final ResourceLocation LOYAL_VILLAGER = LootTableList.register(new ResourceLocation(MinecraftCivProject.MODID, "loyal_villager"));

}
