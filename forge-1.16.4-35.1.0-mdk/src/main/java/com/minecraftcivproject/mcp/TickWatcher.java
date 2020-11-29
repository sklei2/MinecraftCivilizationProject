package com.minecraftcivproject.mcp;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
