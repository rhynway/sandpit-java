package name.rhyn.sandpit.java.concurrency;

public class Counter {

  private Integer value = 0;

  public void increment() {
    ++value;
  }

  public Integer getValue() {
    return value;
  }

}
