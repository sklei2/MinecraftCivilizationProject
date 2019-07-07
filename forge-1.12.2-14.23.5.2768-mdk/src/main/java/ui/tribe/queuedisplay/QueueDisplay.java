package ui.tribe.queuedisplay;

import javax.swing.*;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public abstract class QueueDisplay<T> {

    protected Queue<T> queue;

    public QueueDisplay(Queue<T> queue){
        this.queue = queue;
    }

    public List<QueueItem> getDisplay(){
        return queue.stream().map(
                t -> new QueueItem(this.displayElementName(t), this.displayElement(t))).collect(Collectors.toList());
    }

    protected abstract JPanel displayElement(T t);

    protected abstract String displayElementName(T t);
}
