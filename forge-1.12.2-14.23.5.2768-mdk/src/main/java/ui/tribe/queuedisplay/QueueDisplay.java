package ui.tribe.queuedisplay;

import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public abstract class QueueDisplay<T> {

    protected Queue<T> queue;

    public QueueDisplay(Queue<T> queue){
        this.queue = queue;
    }

    public List<String> getDisplay(){
        return queue.stream().map(this::displayElement).collect(Collectors.toList());
    }

    protected abstract String displayElement(T t);
}
