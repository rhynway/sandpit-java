package name.rhyn.sandpit.java.concurrency;

public class CounterCount implements CounterData {

  private final Counter counter = new Counter();

  @Override
  public void run() {
    synchronized (counter) {
      try {
        counter.wait(1000);

      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      increment();
    }
  }

  private void increment() {
    counter.increment();
    System.out.println("New count is " + counter.getValue());
  }

  @Override
  public int getCount() {
    return counter.getValue();
  }

}
