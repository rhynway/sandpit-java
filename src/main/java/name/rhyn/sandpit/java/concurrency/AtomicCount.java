package name.rhyn.sandpit.java.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCount implements CounterData {

  private final AtomicInteger count = new AtomicInteger(0);

  @Override
  public void run() {
    try {
      Thread.sleep(1000);

    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    count.incrementAndGet();
  }

  @Override
  public int getCount() {
    return count.intValue();
  }

}
