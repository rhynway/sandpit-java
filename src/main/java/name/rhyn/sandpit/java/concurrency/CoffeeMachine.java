package name.rhyn.sandpit.java.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CoffeeMachine {

  private final Lock lock = new ReentrantLock();

  public void slowUserMakesCoffee() {
    while (true) {
      if (lock.tryLock()) {
        try {
          System.out.println("Starved thread acquired the lock");
          break;
        } finally {
          lock.unlock();
        }
      }
    }
  }

  public void fastUserMakesCoffee() {
    while (true) {
      lock.lock();
      try {
        System.out.println("Dominating thread acquired the lock");
        // Simulate some work
        Thread.sleep(10);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      } finally {
        lock.unlock();
      }
    }
  }

}
