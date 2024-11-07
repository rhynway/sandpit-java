package name.rhyn.sandpit.java.concurrency;

import java.util.concurrent.RecursiveTask;


public class MyTask extends RecursiveTask<Integer> {

  @Override
  protected Integer compute() {
    System.out.println("Compute a value and return it");
    return 2;
  }
}
