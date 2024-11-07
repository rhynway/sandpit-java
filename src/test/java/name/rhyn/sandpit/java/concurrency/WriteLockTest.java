package name.rhyn.sandpit.java.concurrency;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.junit.jupiter.api.Test;

class WriteLockTest {

  @Test
  void lockAndUnlock() {
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
    Condition condition = writeLock.newCondition();

    Thread waitingForCondition = new Thread(() -> {
      System.out.println("T1 started, taking lock");
      writeLock.lock();
      // Immediately after getting a lock, do a try catch
      try {
        System.out.println("T1 sleeping");
        Thread.sleep(1000);
        System.out.println("T1 starting await");
        condition.await(); // Releases the lock and waits
        System.out.println("T1 restarted from await");

      } catch (InterruptedException e) {
        fail("T1 shouldn't be interrupted");
      } finally {
        System.out.println("T1 releasing lock");
        writeLock.unlock();
      }
    });
    waitingForCondition.start();

    Thread signalCondition = new Thread(() -> {
      System.out.println("T2 starting, about to sleep");
      try {
        Thread.sleep(100);

      } catch (InterruptedException e) {
        fail("T2 shouldn't happen");
      }
      System.out.println("T2 attempting to get lock");
      writeLock.lock();

      try {
        System.out.println("T2 sleeping");
        Thread.sleep(1000);
        condition.signal();
        System.out.println("T2 condition has been signaled");

      } catch (InterruptedException e) {
        System.out.println("T2 shouldn't happen");
      } finally {
        writeLock.unlock();
      }

    });
    signalCondition.start();

    try {
      waitingForCondition.join();
      signalCondition.join();

    } catch (InterruptedException e) {
      fail(e);
    }
  }

}
