package name.rhyn.sandpit.java.concurrency;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class ThreadPoolsTest {

  private final Callable<Double> callable = () -> Math.PI;


  @Test
  void forkJoinPoolExecute() {
    // The ForkJoinPool is an implementation of the Executor interface
    ForkJoinPool pool = new ForkJoinPool(); // Concurrent threads

    MyTask myTask = new MyTask();

    pool.execute(myTask);  // async
    assertEquals(1, pool.getActiveThreadCount());

    ForkJoinTask<Integer> result1 = pool.submit(myTask);// async + future
    ForkJoinTask<Double> result2 = pool.submit(callable);

    try {
      Integer result = result1.get();
      assertEquals(2, result);
      assertEquals(Math.PI, result2.get());

    } catch (InterruptedException | ExecutionException e) {
      fail(e);
    } finally {
      pool.shutdown();
    }
  }

  @Test
  void forkJoinPoolInvoke() {
    ForkJoinPool pool = new ForkJoinPool();

    Integer result1 = pool.invoke(new RandomTask());   // wait
    assertNotNull(result1);

    Integer result2 = pool.invoke(new RandomTask());   // wait
    assertNotNull(result2);

    Integer result3 = pool.invoke(new RandomTask());   // wait
    assertNotNull(result3);

    pool.shutdown();
    await().atMost(5, TimeUnit.SECONDS)
        .until(() -> pool.isShutdown() && 0 == pool.getActiveThreadCount());
  }

}
