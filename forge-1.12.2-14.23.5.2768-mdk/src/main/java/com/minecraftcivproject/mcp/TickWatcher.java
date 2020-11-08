package com.minecraftcivproject.mcp;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TickWatcher {

    private final Runnable runnable;

    public TickWatcher(Runnable runnable){
        this.runnable = runnable;
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void worldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END){
            runnable.run();
        }
    }
}
