package ui.tribe.general;


import com.minecraftcivproject.mcp.server.managers.queue.TribeQueue;

import java.util.stream.Collectors;

public class TribeQueueItemsUi<T> extends DisplayWithList {

    private final TribeQueue<T> tribeQueue;

    public TribeQueueItemsUi(TribeQueue<T> tribeQueue){
        super("Items");

        this.tribeQueue = tribeQueue;
    }

    public void updateList(){
        super.updateList(tribeQueue.getAllItems().stream().map(i -> tribeQueue.getQueueItemDisplayer().getName(i)).collect(Collectors.toList()));
    }

    @Override
    protected void selectContent(int index, String selectedValue) {
        updateList();
        super.updateContent(tribeQueue.getQueueItemDisplayer().getDisplay(tribeQueue.getAllItems().get(index)));
        super.selectContent(index, selectedValue);

        System.out.println("Revalidate Items:selectContent");
        revalidate();
    }
}
