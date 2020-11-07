package ui.tribe.general;

import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;
import com.minecraftcivproject.mcp.server.managers.queue.TribeQueue;
import com.minecraftcivproject.mcp.server.managers.queue.TribeQueueEnum;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TribeQueuesUi extends DisplayWithList {

    private Map<String, TribeQueueItemsUi> tribeQueueMap = new HashMap<>();

    public TribeQueuesUi(){
        super("Queues");
    }

    public void updateQueueListing(QueueManager queueManager){
        for(TribeQueue<?> tribeQueue: queueManager.getAllQueues()){
            TribeQueueItemsUi<?> tribeQueueItemsUi = new TribeQueueItemsUi<>(tribeQueue);

            tribeQueueItemsUi.setVisible(true);
            tribeQueueItemsUi.add(new JLabel(tribeQueue.getTribeQueueEnum().name() + " Queue"));

            tribeQueueMap.put(tribeQueue.getTribeQueueEnum().name(), tribeQueueItemsUi);
        }

        super.updateList(queueManager.getAllTypes().stream().map(Enum::name).collect(Collectors.toList()));

        System.out.println("Revalidate Queues:updateQueueListing");
        repaint();
    }

    public void updateQueue(TribeQueueEnum tribeQueueEnum){
        tribeQueueMap.get(tribeQueueEnum.name()).repaint();

        System.out.println("Revalidate Queues:updateQueue");
    }

    @Override
    protected void selectContent(int index, String selectedValue) {
        tribeQueueMap.get(selectedValue).updateList();
        super.updateContent(tribeQueueMap.get(selectedValue));
        super.selectContent(index, selectedValue);

        System.out.println("Revalidate Queues:selectContent");
        revalidate();
    }
}

