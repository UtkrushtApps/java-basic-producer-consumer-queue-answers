public class Consumer implements Runnable {
    private final MessageQueue queue;

    public Consumer(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String message = queue.take();
                if(message == null) {
                    // Shutdown signaled and queue is empty
                    break;
                }
                System.out.println("[Consumer] Consumed: " + message);
                Thread.sleep(100); // simulate processing time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("[Consumer] Consumer thread shutting down.");
    }
}
