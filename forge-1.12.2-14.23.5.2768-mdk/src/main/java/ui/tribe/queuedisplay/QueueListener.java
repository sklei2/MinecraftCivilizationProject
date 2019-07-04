package ui.tribe.queuedisplay;

import com.minecraftcivproject.mcp.server.managers.building.construction.ConstructionProject;
import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;
import com.minecraftcivproject.mcp.server.managers.queue.TribeQueue;
import ui.tribe.general.TribeQueuesUi;

import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

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

        updateQueue(queueManager, (TribeQueue)(o));
    }

    private void updateQueueListings(QueueManager queueManager){
        tribeQueuesUi.updateQueueListing(queueManager.getQueueTypes());
    }

    private void updateQueue(QueueManager queueManager, TribeQueue tribeQueue){
        switch (tribeQueue){
            case CONSTRUCTION:
                Queue<ConstructionProject> constructionProjectQueue =
                        queueManager.getQueue(ConstructionProject.class);

                QueueDisplay<ConstructionProject> queueDisplay = new ConstructionQueueDisplay(constructionProjectQueue);

                tribeQueuesUi.updateQueue(tribeQueue, queueDisplay);
        }

    }
}
