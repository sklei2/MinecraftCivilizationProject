package com.minecraftcivproject.mcp.server.managers.building;

import com.minecraftcivproject.mcp.server.managers.TickableManager;
import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;

public class OperableBuildingManager implements TickableManager {

    private final QueueManager queueManager;

    public OperableBuildingManager(QueueManager queueManager){
        this.queueManager = queueManager;
    }

    @Override
    public void onTick() {

    }
}
