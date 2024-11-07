package name.rhyn.sandpit.java.concurrency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.junit.jupiter.api.Test;

class BlockingQueueTest {

  private static final BlockingQueue<String> sharedQueue = new ArrayBlockingQueue<>(20);

  @Test
  void putAndTake() throws InterruptedException {
    Runnable adder = new QueueProducer("Producer 1", sharedQueue);
    Thread threadOne = new Thread(adder);

    Runnable adderTwo = new QueueProducer("Producer 2", sharedQueue);
    Thread threadTwo = new Thread(adderTwo);

    threadOne.start();
    threadTwo.start();

    threadOne.join();
    threadTwo.join();
    assertEquals(20, sharedQueue.size());

    while (!sharedQueue.isEmpty()) {
      assertNotNull(sharedQueue.take());
    }
  }

}
