package com.minecraftcivproject.mcp.server.managers.queue;

import com.minecraftcivproject.mcp.server.managers.building.construction.ConstructionProject;
import com.minecraftcivproject.mcp.server.managers.resource.ItemRequest;
import ui.tribe.queuedisplay.construction.ConstructionProjectDisplayer;

import java.util.*;

public class QueueManager {

    private final TribeQueue<ConstructionProject> constructionProjectQueue;
    private final TribeQueue<ItemRequest> itemRequestQueue;

    public QueueManager(){
        this.constructionProjectQueue = new TribeQueue<>(new LinkedList<>(), TribeQueueEnum.CONSTRUCTION, new ConstructionProjectDisplayer());
        this.itemRequestQueue = new TribeQueue<>(new LinkedList<>(), TribeQueueEnum.ITEM_REQUEST, null);
    }

    public TribeQueue<ConstructionProject> getConstructionProjectQueue() {
        return constructionProjectQueue;
    }

    public TribeQueue<ItemRequest> getItemRequestQueue() {
        return itemRequestQueue;
    }
}
