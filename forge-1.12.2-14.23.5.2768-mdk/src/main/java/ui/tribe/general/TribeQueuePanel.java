package ui.tribe.general;


import ui.tribe.queuedisplay.QueueDisplay;
import ui.tribe.queuedisplay.QueueItem;

import java.util.List;
import java.util.stream.Collectors;

public class TribeQueuePanel extends DisplayWithList {

    private List<QueueItem> queueItems;

    public TribeQueuePanel(){
        super("Items");
    }

    public void display(QueueDisplay<?> queueDisplay){
        queueItems = queueDisplay.getDisplay();

        super.updateList(queueItems.stream().map(queueItem -> queueItem.getName()).collect(Collectors.toList()));

        revalidate();
    }

    @Override
    protected void selectContent(int index, String selectedValue) {
        super.updateContent(queueItems.get(index).getDisplay());
    }
}
