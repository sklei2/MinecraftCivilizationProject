package ui.tribe.queuedisplay;

import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;
import com.minecraftcivproject.mcp.server.managers.queue.TribeQueueEnum;
import ui.tribe.general.TribeQueuesUi;

import java.util.Observable;
import java.util.Observer;

public class QueueListener implements Observer {

    private TribeQueuesUi tribeQueuesUi;

    public QueueListener(TribeQueuesUi tribeQueuesUi){
        this.tribeQueuesUi = tribeQueuesUi;
    }

    @Override
    public void update(Observable observable, Object o) {
        System.out.println("notify listener");

        QueueManager queueManager = (QueueManager)observable;

        if(o == null){
            updateQueueListings(queueManager);
            return;
        }

        tribeQueuesUi.updateQueue((TribeQueueEnum)(o));
    }

    private void updateQueueListings(QueueManager queueManager){
        tribeQueuesUi.updateQueueListing(queueManager);
    }
}
