package name.rhyn.sandpit.java.concurrency;

public class IntCount implements CounterData {

  private int count = 0;

  @Override
  public void run() {
    synchronized (Thread.currentThread()) {
      try {
        Thread.currentThread().wait(1000);

      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      increment();
    }
  }

  private void increment() {
    count++;
  }

  @Override
  public int getCount() {
    return count;
  }

}
