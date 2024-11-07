package name.rhyn.sandpit.java.concurrency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.Exchanger;
import org.junit.jupiter.api.Test;

class ExchangerTest {

  @Test
  void exchanger() throws InterruptedException {
    Exchanger<String> exchanger = new Exchanger<>();

    Thread threadOne = new Thread(() -> {
      try {
        Thread.sleep(1000);
        String received = exchanger.exchange("Message One");
        System.out.println("Thread one received: " + received);
        assertEquals("Message Two", received);

      } catch (InterruptedException e) {
        fail(e.getMessage());
      }
    });

    Thread threadTwo = new Thread(() -> {
      try {
        Thread.sleep(3000);
        String received = exchanger.exchange("Message Two");
        System.out.println("Thread two received: " + received);
        assertEquals("Message One", received);

      } catch (InterruptedException e) {
        fail(e.getMessage());
      }
    });

    threadOne.start();
    threadTwo.start();

    threadOne.join();
    threadTwo.join();
  }

}
