package ui.tribe.general;

import com.minecraftcivproject.mcp.server.managers.queue.TribeQueue;
import ui.tribe.queuedisplay.QueueDisplay;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TribeQueuesUi extends DisplayWithList {

    private Map<String, TribeQueuePanel> tribeQueueMap = new HashMap<>();

    public TribeQueuesUi(){
        super("Queues");
    }

    public void updateQueueListing(List<TribeQueue> queues){
        for(TribeQueue tribeQueue : queues){
            TribeQueuePanel tribeQueuePanel = new TribeQueuePanel();

            tribeQueuePanel.setVisible(true);
            tribeQueuePanel.add(new JLabel(tribeQueue.name() + " Queue"));

            tribeQueueMap.put(tribeQueue.name(), tribeQueuePanel);
        }

        super.updateList(queues.stream().map(Enum::name).collect(Collectors.toList()));

        revalidate();
    }

    public void updateQueue(TribeQueue queue, QueueDisplay<?> queueDisplay) {
        tribeQueueMap.get(queue.name()).display(queueDisplay);

        revalidate();
    }

    @Override
    protected void selectContent(String selectedValue) {
        super.updateContent(tribeQueueMap.get(selectedValue));

        revalidate();
    }
}

