package com.minecraftcivproject.mcp.server.managers.queue;

import ui.tribe.queuedisplay.QueueItemDisplayer;

import java.util.*;

public class TribeQueue<T> extends Observable {

    private final Queue<T> queue;
    private final TribeQueueEnum tribeQueueEnum;
    private final QueueItemDisplayer<T> queueItemDisplayer;

    public TribeQueue(Queue<T> queue, TribeQueueEnum tribeQueueEnum, QueueItemDisplayer<T> queueItemDisplayer){
        this.queue = queue;
        this.tribeQueueEnum = tribeQueueEnum;
        this.queueItemDisplayer = queueItemDisplayer;
    }

    public List<T> getAllItems(){
        return new ArrayList<>(queue);
    }

    public void queue(T item){
        System.out.println("queueing object");
        queue.add(item);

        setChanged();
        notifyObservers(tribeQueueEnum);
    }

    public T dequeue(){
        T item = queue.remove();

        setChanged();
        notifyObservers(tribeQueueEnum);

        return item;
    }

    public boolean hasNext(){
        return queue.size() > 0;
    }

    public TribeQueueEnum getTribeQueueEnum() {
        return tribeQueueEnum;
    }

    public QueueItemDisplayer<T> getQueueItemDisplayer(){
        return this.queueItemDisplayer;
    }
}
