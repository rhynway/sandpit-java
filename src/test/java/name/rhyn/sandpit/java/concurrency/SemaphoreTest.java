package name.rhyn.sandpit.java.concurrency;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.Semaphore;
import org.junit.jupiter.api.Test;

public class SemaphoreTest {

  @Test
  void semaphore() throws InterruptedException {
    Semaphore semaphore = new Semaphore(1);
    Thread thread = new Thread(() -> {
      for (int i = 0; i < 3; i++) {
        try {
          semaphore.acquire();
        } catch (InterruptedException e) {
          fail(e.getMessage());
        }
        System.out.println("Yes");
      }
    });
    thread.start();
    for (int i = 0; i < 3; i++) {
      semaphore.release();
      Thread.sleep(1000 + (int) (Math.random() * 2000));
    }
  }

}
