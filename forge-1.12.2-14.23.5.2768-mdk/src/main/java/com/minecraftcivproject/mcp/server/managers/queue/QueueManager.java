package com.minecraftcivproject.mcp.server.managers.queue;

import java.util.*;

public class QueueManager extends Observable implements Observer{

    private Map<TribeQueueEnum, TribeQueue<? extends Queueable>> queues;

    public QueueManager(){
        queues = new HashMap<>();
    }

    public void addQueue(TribeQueue<? extends Queueable> tribeQueue){
        System.out.println("added queue");

        queues.put(tribeQueue.getTribeQueueEnum(), tribeQueue);

        setChanged();
        notifyObservers();
    }

    public TribeQueue<?> getQueue(TribeQueueEnum tribeQueueEnum){
        return queues.get(tribeQueueEnum);
    }

    public Collection<TribeQueue<? extends Queueable>> getAllQueues(){
        return queues.values();
    }

    public Collection<TribeQueueEnum> getAllTypes(){
        return queues.keySet();
    }

    @Override
    public void update(Observable observable, Object o) {
        System.out.println("queue manager sees update");
        setChanged();
        notifyObservers();
    }
}
