package com.minecraftcivproject.mcp.server.managers.queue;

import java.util.*;

public class QueueManager extends Observable {

    private Map<TribeQueue, Queue<Object>> queues;
    private Map<TribeQueue, Class> queueTypes;

    public QueueManager(){
        queues = new HashMap<>();
        queueTypes = new HashMap<>();
    }

    public void addQueue(TribeQueue queueName, Class queueType){
        System.out.println("added queue");
        Queue<Object> newQueue = new LinkedList<>();
        queues.put(queueName, newQueue);
        queueTypes.put(queueName, queueType);

        setChanged();
        notifyObservers();
    }

    public void queue(TribeQueue queueName, Object o){
        System.out.println("queueing object");
        queues.get(queueName).add(o);

        setChanged();
        notifyObservers(queueName);
    }

    public <T> T dequeue(TribeQueue queueName){
        Object o = queues.get(queueName).remove();

        //wow is this fun
        Class<T> type = queueTypes.get(queueName);

        setChanged();
        notifyObservers(queueName);

        return type.cast(o);
    }

    public List<TribeQueue> getQueueTypes(){
        return new ArrayList<>(queueTypes.keySet());
    }

    public <T> Queue<T> getQueue (Class<T> c){
        TribeQueue queueType = queueTypes.keySet().stream().filter(key -> queueTypes.get(key).equals(c)).findFirst().get();

        //spicy
        return (Queue<T>)queues.get(queueType);
    }
}
