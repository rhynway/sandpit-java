package name.rhyn.sandpit.java.concurrency;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ConcurrentTest {

  private final Lock lockOne = new ReentrantLock();
  private final Lock lockTwo = new ReentrantLock();
  private int counter = 0;

  /**
   * Deadlock and livelock are similar, although in a deadlock situation the threads are stuck
   * waiting, rather than being active or performing any work.
   */
  @Disabled("Never stops because of a deadlock")
  @Test
  void deadlock() {
    TwoDoorLock twoDoorFromInside = new TwoDoorLock(lockOne, lockTwo);
    TwoDoorLock twoDoorFromOutside = new TwoDoorLock(lockTwo, lockOne);

    Thread thread1 = new Thread(twoDoorFromInside::pass);
    Thread thread2 = new Thread(twoDoorFromOutside::pass);

    thread1.start();
    thread2.start();

    try {
      thread1.join();
      thread2.join();

    } catch (InterruptedException e) {
      fail(e);
    }
  }

  /**
   * Resource starvation is when a single active thread is perpetually unable to gain access to a
   * shared resource.
   * <p>
   * Livelock is a special case of resource starvation, in which two or more active threads are
   * * unable to gain access to shared resources, repeating the process over and over again.
   */
  @Disabled("Never stops because of an endless loop")
  @Test
  void starvation() {
    var coffeeMachine = new CoffeeMachine();

    Thread starvedThread = new Thread(coffeeMachine::slowUserMakesCoffee);
    starvedThread.setPriority(Thread.MIN_PRIORITY);

    Thread dominatingThread = new Thread(coffeeMachine::fastUserMakesCoffee);
    dominatingThread.setPriority(Thread.MAX_PRIORITY);

    starvedThread.start();
    dominatingThread.start();

    try {
      starvedThread.join();
      dominatingThread.join();
    } catch (InterruptedException e) {
      fail(e);
    }
  }

  /**
   * A race condition is an undesirable result when two tasks that should be completed sequentially
   * are completed at the same time. The result is often corruption of data in some way. If two
   * threads are both modifying the same int variable and there is no synchronization, then a race
   * condition can occur with one of the writes being lost.
   */
  @Test
  void raceCondition() {
    Thread thread1 = new Thread(this::increment);
    Thread thread2 = new Thread(this::increment);

    thread1.start();
    thread2.start();

    try {
      thread1.join();
      thread2.join();

    } catch (InterruptedException e) {
      fail(e);
    }

    assertTrue(counter > 0); // The final value can be different in several runs.
  }

  private void increment() {
    while (counter < 100) {
      counter++;
    }
  }

}
