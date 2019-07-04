package com.minecraftcivproject.mcp.proxy;

import com.minecraftcivproject.mcp.common.initialization.register.BlueprintRegisterer;
import com.minecraftcivproject.mcp.common.initialization.register.TownBlueprintRegisterer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ui.tribe.UiFrame;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);

        new BlueprintRegisterer().register();
        new TownBlueprintRegisterer().register();

        new UiFrame().setVisible(true);
    }
}