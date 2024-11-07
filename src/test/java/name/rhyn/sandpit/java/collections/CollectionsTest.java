package name.rhyn.sandpit.java.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * - TreeMap and TreeSet sort as you insert elements.
 * - TreeMap sorts the keys and TreeSet sorts the objects in the set.
 */
class CollectionsTest {

  @Test
  void testBinarySearchExisting() {
    List<Integer> numbers = new ArrayList<>(List.of(10, 3, 21, 27, 4, 8, 12, 29, 15));

    assertEquals(List.of(10, 3, 21, 27, 4, 8, 12, 29, 15), numbers);

    int searchedNumber = 27;

    int position = Collections.binarySearch(numbers, searchedNumber);
    // The value of position isn't predictable

    Collections.sort(numbers);

    assertEquals(List.of(3, 4, 8, 10, 12, 15, 21, 27, 29), numbers);
    int correctPosition = Collections.binarySearch(numbers, searchedNumber);
    assertEquals(7, correctPosition);
  }

  @Test
  void unmodifiableCollection() {
    List<String> original = new ArrayList<>();
    original.add("A");
    original.add("B");

    Collection<String> unmodifiable = Collections.unmodifiableCollection(original);
    assertThrows(UnsupportedOperationException.class, () -> unmodifiable.add("C"));
  }

  @Test
  void synchronizedCollection() {
    Set<String> original = new HashSet<>();
    Collection<String> synchronizedCollection = Collections.synchronizedCollection(original);

    synchronized (synchronizedCollection) {
      synchronizedCollection.add("A");
      synchronizedCollection.add("B");
    }

    assertEquals(2, synchronizedCollection.size());
    assertEquals("[A, B]", original.toString());
  }

  @Test
  void reverseOrder() {
    List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3));
    Collections.sort(numbers, Collections.reverseOrder());

    assertEquals(List.of(3, 2, 1), numbers);
  }

  @Test
  void shuffle() {
    List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5));
    Collections.shuffle(numbers);

    assertEquals(5, numbers.size());
  }

}
