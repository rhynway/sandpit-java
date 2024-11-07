package name.rhyn.sandpit.java.concurrency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;

class ExecutorServiceTest {

  private final Runnable runnable = () -> System.out.println("I'm running");

  private final Callable<Double> callable = () -> Math.PI;

  @Test
  void executorService() throws ExecutionException, InterruptedException {
    java.util.concurrent.ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<?> futureOfRunnable = executorService.submit(runnable);
    Future<Double> futureOfCallable = executorService.submit(callable);

    assertNull(futureOfRunnable.get()); // Waits for the computation to complete
    assertEquals(Math.PI, futureOfCallable.get());

    executorService.shutdown();
  }

}
