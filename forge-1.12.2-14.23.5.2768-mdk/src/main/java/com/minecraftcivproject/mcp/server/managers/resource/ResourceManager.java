package com.minecraftcivproject.mcp.server.managers.resource;

import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;

public class ResourceManager {
    // Equivalent to a ConstructionProject (is a queue type)

    private final QueueManager queueManager;

    public ResourceManager(QueueManager queueManager){
        this.queueManager = queueManager;
    }


}
