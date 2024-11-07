package name.rhyn.sandpit.java.concurrency;

public class IntegerCount implements CounterData {

  Integer count = 0;

  @Override
  public void run() {
    synchronized (this) {
      try {
        this.wait(1000);

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
