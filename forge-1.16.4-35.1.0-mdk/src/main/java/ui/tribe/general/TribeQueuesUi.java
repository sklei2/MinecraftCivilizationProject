package ui.tribe.general;

import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;
import com.minecraftcivproject.mcp.server.managers.queue.TribeQueueEnum;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TribeQueuesUi extends DisplayWithList {

    private final QueueManager queueManager;

    public TribeQueuesUi(QueueManager queueManager){
        super("Queues", Arrays.stream(TribeQueueEnum.values()).map(Enum::name).collect(Collectors.toList()));

        this.queueManager = queueManager;
    }

    @Override
    protected void selectContent(int index, String selectedValue) {
        TribeQueueEnum tribeQueueEnum = TribeQueueEnum.valueOf(selectedValue);

        switch (tribeQueueEnum){
            case CONSTRUCTION:
                super.updateContent(new TribeQueueItemsUi<>(queueManager.getConstructionProjectQueue()));
                break;
            case ITEM_REQUEST:
                super.updateContent(new TribeQueueItemsUi<>(queueManager.getItemRequestQueue()));
                break;
            default:
                throw new IllegalArgumentException();
        }

        super.selectContent(index, selectedValue);

        System.out.println("Revalidate Queues:selectContent");
        revalidate();
    }
}

