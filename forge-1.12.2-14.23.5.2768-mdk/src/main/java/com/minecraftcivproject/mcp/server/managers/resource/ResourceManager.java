package com.minecraftcivproject.mcp.server.managers.resource;

import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;
import com.minecraftcivproject.mcp.server.managers.queue.TribeQueue;

public class ResourceManager {
    // Equivalent to a ConstructionProject (is a queue type)

    private final QueueManager queueManager;

    public ResourceManager(QueueManager queueManager){
        this.queueManager = queueManager;
        this.queueManager.addQueue(TribeQueue.RESOURCE_REQUEST, ResourceRequest.class);
    }


}
