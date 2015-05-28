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
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
   	 	MinecraftForge.EVENT_BUS.register(new SturdyStickDropManager());
   	    	     	 
   	 	ModItems.createItems(); // create all the mod items
   	 	ItemRenderRegister.registerItemRenderer(); // register them...
  
   	 	
		int redColor = (255 << 16);
		int orangeColor = (255 << 16)+ (200 << 8);
		EntityRegistry.registerGlobalEntityID(KingVillager.class, "King Villager", EntityRegistry.findGlobalUniqueEntityId(), redColor, orangeColor);
		EntityRegistry.registerGlobalEntityID(PeasantVillager.class, "Peasant Villager", EntityRegistry.findGlobalUniqueEntityId(), orangeColor, redColor);
   	 
    }
    
   
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	
    	proxy.registerRenderers();
    	ModRecipes.registerRecipes(); //create and register every recipe
    	 
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
        
        public class SturdyStickDropManager{
        	@SubscribeEvent
        	public void onEvent(HarvestDropsEvent event){
        		BlockPos location = event.pos;
        		//write the rest here
        	}
        }
}