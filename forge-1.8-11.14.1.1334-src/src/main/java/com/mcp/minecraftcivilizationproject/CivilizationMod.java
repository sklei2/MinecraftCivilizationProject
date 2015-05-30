package com.mcp.minecraftcivilizationproject;

import ibxm.Player;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import com.google.common.base.Predicate;
import com.mcp.client.render.items.ItemRenderRegister;
import com.mcp.minecraftcivilizationproject.items.ItemExplodingArrow;
import com.mcp.minecraftcivilizationproject.items.ItemExplodingBow;
import com.mcp.minecraftcivilizationproject.items.ModItems;
import com.mcp.minecraftcivilizationproject.recipes.ModRecipes;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityEvent.EnteringChunk;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.util.BlockPos;
import net.minecraft.village.Village;
import net.minecraft.village.VillageCollection;
import net.minecraft.world.World;
import net.minecraft.world.WorldManager;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.SpecialSpawn;
import net.minecraft.server.management.PlayerManager;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

@Mod(modid = Reference.MOD_ID, version = Reference.VERSION)
public class CivilizationMod {
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	boolean preinitDone = false;

	// entities and items should be registered here!
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		MinecraftForge.EVENT_BUS.register(new SturdyStickDropManager());

		preinitDone = true;
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderers();
		proxy.registerRecipes();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {

	}

	public class SturdyStickDropManager {
		@SubscribeEvent
		public void onEvent(HarvestDropsEvent event) {
			// a stack i'm going to compare the events to.
			ItemStack saplingStack = new ItemStack(
					Item.getItemFromBlock(Blocks.sapling), 1);
			boolean needsAStick = false;
			for (ItemStack dropped : event.drops) { // for every stack the block
													// drops...
				/*
				 * Since comparing items in a stack relies on the damage of the
				 * item I need to get the damage of the item that dropped. Then
				 * say that my sapling also has the same damage.
				 */
				int droppedItemDamage = dropped.getItem().getDamage(dropped);
				saplingStack.getItem().setDamage(saplingStack,
						droppedItemDamage);
				if (dropped.isItemEqual(saplingStack)) { // if it drops a
															// sapling
					// then it also drops a sturdy stick.
					needsAStick = true;
				}
			}
			if (needsAStick) {
				event.drops.add(new ItemStack(Item
						.getByNameOrId(Reference.MOD_ID + ":"
								+ Reference.MOD_ID + "_SturdyStick"), 1));
			}
		}
	}
}