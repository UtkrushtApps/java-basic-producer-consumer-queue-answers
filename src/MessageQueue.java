import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private final Queue<String> queue = new LinkedList<>();
    private boolean shutdown = false;

    public synchronized void put(String message) {
        queue.add(message);
        notifyAll();
    }

    public synchronized String take() throws InterruptedException {
        while (queue.isEmpty() && !shutdown) {
            wait();
        }
        if (queue.isEmpty() && shutdown) {
            return null;
        }
        return queue.poll();
    }

    public synchronized void shutdown() {
        shutdown = true;
        // Wake up any waiting thread
        notifyAll();
    }
}
