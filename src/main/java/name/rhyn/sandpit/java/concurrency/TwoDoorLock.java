package name.rhyn.sandpit.java.concurrency;

import java.util.concurrent.locks.Lock;

public class TwoDoorLock {

  private final Lock lockOne;
  private final Lock lockTwo;

  public TwoDoorLock(Lock lockOne, Lock lockTwo) {
    this.lockOne = lockOne;
    this.lockTwo = lockTwo;
  }

  public void pass() {
    lockOne.lock();
    try {
      // Simulate some work with lock1
      Thread.sleep(50);
      lockTwo.lock();

      try {
        // Simulate some work with lock2
      } finally {
        lockTwo.unlock();
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally {
      lockOne.unlock();
    }
  }

}
