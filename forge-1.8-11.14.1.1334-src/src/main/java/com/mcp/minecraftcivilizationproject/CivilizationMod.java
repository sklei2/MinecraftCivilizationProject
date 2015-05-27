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

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityEvent.EnteringChunk;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
public class CivilizationMod
{
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
	
	
    WorldServer w;
    public String hostname;
    MinecraftServer ms;
    PlayerManager pm;
    EntityPlayer host;
    KingVillager king;
    CivilizationManager manager;
    
    // entities and items should be registered here!
    @EventHandler
    public void preinit(FMLInitializationEvent event){
    	
    	MinecraftForge.TERRAIN_GEN_BUS.register(new WorldLoadEventHandler());
   	 	MinecraftForge.EVENT_BUS.register(new WorldLoadEventHandler());
   	 	MinecraftForge.EVENT_BUS.register(manager = new CivilizationManager());
   	    	     	 
   	 	ModItems.createItems(); // create all the mod items
   	 	ItemRenderRegister.registerItemRenderer(); // register them...
  
   	 	
		int redColor = (255 << 16);
		int orangeColor = (255 << 16)+ (200 << 8);
		EntityRegistry.registerGlobalEntityID(KingVillager.class, "King Villager", EntityRegistry.findGlobalUniqueEntityId(), redColor, orangeColor);
		EntityRegistry.registerGlobalEntityID(PeasantVillager.class, "Peasant Villager", EntityRegistry.findGlobalUniqueEntityId(), orangeColor, redColor);
   	 
    }
    
    // recipes should be initialized here!
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	
    	proxy.registerRenderers();
    	//TODO learn how to do textures and icons...
    	 
    	// This should make a recipe for the exploding arrow.
    	 
    	//what you get from the recipe
    	ItemStack explodingArrowStack = new ItemStack(Item.getByNameOrId("ExplodingArrow"),2);
    	// what you need
    	ItemStack redStoneTorchStack = new ItemStack(Blocks.redstone_torch);
    	ItemStack gunpowderStack = new ItemStack(Item.getByNameOrId("gunpowder"),3);
    	ItemStack featherStack = new ItemStack(Item.getByNameOrId("feather"));
    	 
    	/*
    	 * To make, it should be...
    	 * |3 x gunpowder    | | |
    	 * |1 redstone torch | | |   -> 2 x Exploding Arrow
    	 * |1 feather        | | |
    	 */
    	GameRegistry.addRecipe(explodingArrowStack,
    			"x",
    			"y",
    			"z",
    			'x',gunpowderStack,'y',redStoneTorchStack,'z',featherStack);
    	 
    	
    	// This should make a recipe for the exploding bow.
    	ItemStack stringStack = new ItemStack(Item.getByNameOrId("string"));
    	ItemStack explodingBowStack = new ItemStack(Item.getByNameOrId("ExplodingBow"));
    	/*
    	 * To make, it should be...
    	 * |1 redstone torch |                 | |
    	 * |1 string         | 1 redstone torch| |
    	 * |1 redstone torch |                 | | 
    	 */
    	GameRegistry.addRecipe(explodingBowStack,
    			"x",
    			"yx",
    			"x",
    			'x',redStoneTorchStack,'y',stringStack);
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event){
      
    }
    
    public class WorldLoadEventHandler{
        @SubscribeEvent
    	    public void onEvent(WorldEvent.Load event){
    	    	w = DimensionManager.getWorld(0);
    	    	
    	    	//clean the world
    	    	Predicate p = new Predicate<Object>() {
					@Override
					public boolean apply(Object input) {
						return true;
					}
				};
    	    	List<KingVillager> kings = w.getEntities(KingVillager.class, p);
    	    	List<PeasantVillager> peasants = w.getEntities(PeasantVillager.class, p);
    	    	for(KingVillager king : kings){
    	    		king.setDead();
    	    	}
    	    	for(PeasantVillager peasant : peasants){
    	    		peasant.setDead();
    	    	}
    	    	pm = w.getPlayerManager();
    	    	ms = w.getMinecraftServer();
    	    	hostname = ms.getServerOwner();
    	    	System.out.println(hostname);
    	    }
    }
        
        public class CivilizationManager{
            @SubscribeEvent
        	    public void onEvent(EntityJoinWorldEvent event){
            		if(event.entity instanceof KingVillager){
            			System.out.println("Begin the game");
            			king = (KingVillager)event.entity;
            			king.foundTown();
            		}else if(event.entity instanceof PeasantVillager){
            			if(king == null || king.isDead){
            				//Dies from lack of leadership
            				event.entity.setDead();
            				return;
            			}
            			PeasantVillager peasant = (PeasantVillager)event.entity; 
            			king.addLoyalSubject(peasant);            			
            		}
            	}
        }
}