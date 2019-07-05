package ui.tribe.queuedisplay;

import javax.swing.*;

public class QueueItem {

    private final String displayName;
    private final JPanel display;

    public QueueItem(String displayName, JPanel display){
        this.displayName = displayName;
        this.display = display;
    }

    public String getName(){
        return this.displayName;
    }

    public JPanel getDisplay(){
        return this.display;
    }
}
