package name.rhyn.sandpit.java.concurrency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class CounterTest {

  static Stream<Runnable> counter() {
    return Stream.of(new IntCount(), new IntegerCount(), new CounterCount(), new AtomicCount());
  }

  @ParameterizedTest
  @MethodSource("counter")
  void increment(CounterData counter) {
    var t1 = new Thread(counter);
    var t2 = new Thread(counter);
    var t3 = new Thread(counter);

    t1.start();
    t2.start();
    t3.start();

    try {
      t1.join();
      t2.join();
      t3.join();

      assertEquals(3, counter.getCount());
    } catch (InterruptedException e) {
      fail(e);
    }
  }

}
