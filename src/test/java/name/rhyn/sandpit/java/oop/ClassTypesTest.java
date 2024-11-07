package name.rhyn.sandpit.java.oop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import org.junit.jupiter.api.Test;

public class ClassTypesTest {

  @Test
  void anonymousClass() {
    int count = 0;
    Comparator<String> comparator = new Comparator<>() {

      @Override
      public int compare(String o1, String o2) {
        System.out.println(count);
        // count = count + 10; Not possible
        return o1.compareTo(o2);
      }
    };

    assertEquals(-43, comparator.compare("Are you", "learning?"));

    //
    Comparator<String> lambdaComparator = (o1, o2) -> o1.compareTo(o2);

    assertEquals(-1, lambdaComparator.compare("a", "b"));
    assertEquals(1, lambdaComparator.compare("b", "a"));
  }

}
