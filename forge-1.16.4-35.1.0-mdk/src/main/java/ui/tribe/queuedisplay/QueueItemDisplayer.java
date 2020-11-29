package ui.tribe.queuedisplay;

import javax.swing.*;

public abstract class QueueItemDisplayer<T> {
    public abstract String getName(T item);
    public abstract JPanel getDisplay(T item);
}
