package name.rhyn.sandpit.java.concurrency;

import java.util.concurrent.BlockingQueue;

public class QueueProducer implements Runnable {

  private final String text;
  private final BlockingQueue<String> sharedQueue;

  public QueueProducer(String text, BlockingQueue<String> sharedQueue) {
    this.text = text;
    this.sharedQueue = sharedQueue;
  }

  @Override
  public void run() {
    for (int i = 1; i <= 10; i++) {

      try {
        sharedQueue.put(text + " " + i);

      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

}

