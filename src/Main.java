public class Main {
    public static void main(String[] args) {
        final int numProducers = 3;
        final int messagesPerProducer = 5;

        MessageQueue queue = new MessageQueue();

        Thread consumerThread = new Thread(new Consumer(queue), "Consumer-1");
        consumerThread.start();

        Thread[] producerThreads = new Thread[numProducers];
        for(int i = 0; i < numProducers; i++) {
            Producer producer = new Producer("P" + (i+1), queue, messagesPerProducer);
            producerThreads[i] = new Thread(producer, "Producer-" + (i+1));
            producerThreads[i].start();
        }

        // Wait for all producers to finish
        for(Thread producerThread : producerThreads) {
            try {
                producerThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Signal shutdown to the consumer
        queue.shutdown();

        // Wait for consumer to shutdown
        try {
            consumerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("All threads have shut down cleanly.");
    }
}
