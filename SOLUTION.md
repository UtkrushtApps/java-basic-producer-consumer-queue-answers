# Solution Steps

1. Replace the ArrayList queue with a private LinkedList queue in a MessageQueue class, synchronized for safe use between threads.

2. Implement the put method in MessageQueue to add a message and notify waiting threads.

3. Implement the take method in MessageQueue to wait (without busy loop) for a message to appear, or exit cleanly when shutdown is signaled.

4. Implement a shutdown mechanism in MessageQueue that sets a shutdown flag and notifies waiting threads in take().

5. Create the Producer class to repeatedly put messages into the queue and simulate message production with Thread.sleep().

6. Create the Consumer class to repeatedly take and process messages from the queue, sleeping to simulate processing, and correctly exit when shutdown and queue is empty.

7. Write the Main class to start multiple producer threads and one consumer, wait for producers to finish, signal shutdown on the queue, then wait for the consumer to shut down.

8. Ensure all threads are joined and print a confirmation message on clean shutdown.

