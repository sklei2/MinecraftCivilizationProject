package com.minecraftcivproject.mcp.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.logging.Logger;

public class ClientProxy extends CommonProxy {

    private static Logger logger = Logger.getLogger("ClientProxy");

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        logger.info("oh hey what's up?");
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
}