package com.minecraftcivproject.mcp.server.managers.queue;

import java.util.*;

public class QueueManager {

    Map<TribeQueue, Queue<Object>> queues;
    Map<Queue<Object>, Class> queueTypes;

    public QueueManager(){
        queues = new HashMap<>();
        queueTypes = new HashMap<>();
    }

    public void addQueue(TribeQueue queueName, Class queueType){
        Queue<Object> newQueue = new LinkedList<>();
        queues.put(queueName, newQueue);
        queueTypes.put(newQueue, queueType);
    }

    public void queue(TribeQueue queueName, Object o){
        queues.get(queueName).add(o);
    }

    public <T> T dequeue(TribeQueue queueName){
        Object o = queues.get(queueName);

        //wow is this fun
        Class<T> type = queueTypes.get(queues);

        return type.cast(o);
    }

}
