package name.rhyn.sandpit.java.concurrency;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;

public class RandomTask extends RecursiveTask<Integer> {

  @Override
  protected Integer compute() {
    return ThreadLocalRandom.current()
        .nextInt(1, 21); // Arguments: least (inclusive) and bound (exclusive)
  }
}
