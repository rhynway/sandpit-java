package name.rhyn.sandpit.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class VarargsTest {

  // Method using varargs
  public int sum(int... numbers) {
    int sum = 0;
    for (int number : numbers) {
      sum += number;
    }
    return sum;
  }

  @Test
  void testSum() {
    assertEquals(0, sum());
    assertEquals(1, sum(1));
    assertEquals(6, sum(1, 2, 3));
    assertEquals(10, sum(1, 2, 3, 4));
  }

  /**
   * Supresses Possible heap pollution from parameterized vararg type
   */
  @Test
  void safeVarargs() {
    List<String> list1 = Arrays.asList("Aa", "B", "C");
    List<String> list2 = Arrays.asList("D", "E", "Ff");

    assertEquals(8, printElements(list1, list2));
  }

  @SafeVarargs
  private long printElements(List<String>... groupsOfTexts) {
    return Arrays.stream(groupsOfTexts).flatMap(List::stream).mapToInt(String::length).sum();
  }

}
