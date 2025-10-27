public class Producer implements Runnable {
    private final MessageQueue queue;
    private final int messagesToProduce;
    private final String producerName;

    public Producer(String producerName, MessageQueue queue, int messagesToProduce) {
        this.queue = queue;
        this.messagesToProduce = messagesToProduce;
        this.producerName = producerName;
    }

    @Override
    public void run() {
        try {
            for(int i = 1; i <= messagesToProduce; i++) {
                String message = producerName + " message-" + i;
                queue.put(message);
                System.out.println("[Producer-" + producerName + "] Produced: " + message);
                Thread.sleep(50); // simulate time to produce a message
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
