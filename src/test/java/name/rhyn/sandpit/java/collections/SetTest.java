package name.rhyn.sandpit.java.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

class SetTest {

  private static final String BANANA = "Banana";
  private static final String MANGO = "Mango";
  private static final String LEMON = "Lemon";
  private static final String APPLE = "Apple";
  private static final String KIWI = "Kiwi";

  @Test
  void hashSet() {
    Set<String> letters = new HashSet<>(); // Type must support equals and hashCode
    letters.add("A");
    letters.add("B");
    letters.add("C");

    // No duplicates. If equals isn't overwritten by the type class (here String) the result won't be accurate
    assertFalse(letters.add("B"));
    assertEquals("[A, B, C]", letters.toString());
  }

  @Test
  void treeSet() {
    TreeSet<Character> characters = new TreeSet<>(); // ordering is used to organize items
    characters.add('a');
    characters.add('c');
    characters.add('b');

    assertEquals("[a, b, c]", characters.toString());
    assertEquals('a', characters.lower('b'));

    TreeSet<String> organizedByLength = new TreeSet<>(Comparator.comparingInt(String::length));
    organizedByLength.add("ccc");
    organizedByLength.add("bb");
    organizedByLength.add("a");
    organizedByLength.add("dddd");

    assertEquals("[a, bb, ccc, dddd]", organizedByLength.toString());
  }


  @Test
  void sortedSet() {
    // The TreeSet is sorted
    SortedSet<String> sortedSet = new TreeSet<>();

    sortedSet.add(BANANA);      // Values must implement the Comparable interface
    sortedSet.add(MANGO);
    sortedSet.add(KIWI);
    sortedSet.add(APPLE);
    sortedSet.add(LEMON);

    assertFalse(sortedSet.add(LEMON)); // Can't be added

    assertEquals("[Apple, Banana, Kiwi, Lemon, Mango]", sortedSet.toString());
    assertEquals(APPLE, sortedSet.first());
    assertEquals(MANGO, sortedSet.last());

    SortedSet<String> headSet = sortedSet.headSet(KIWI);
    assertEquals(2, headSet.size()); // Elements that are less than
    assertEquals("[Apple, Banana]", headSet.toString());

    SortedSet<String> tailSet = sortedSet.tailSet(LEMON);
    assertEquals(2, tailSet.size()); // Elements smaller or equal
    assertEquals("[Lemon, Mango]", tailSet.toString());

    SortedSet<String> subSet = sortedSet.subSet(BANANA, LEMON);
    assertEquals(2, subSet.size());
    assertEquals("[Banana, Kiwi]", subSet.toString());
  }

  @Test
  void copyOf() {
    Set<String> immutableFruits = Set.of(KIWI, LEMON, MANGO);

    assertThrows(UnsupportedOperationException.class, () -> immutableFruits.add(BANANA));

    Set<String> sameFruits = Set.copyOf(immutableFruits);

    assertTrue(immutableFruits == sameFruits);

    Set<String> mutableFruits = new HashSet<>(immutableFruits);

    assertFalse(immutableFruits == mutableFruits);

    mutableFruits.add(BANANA);
    assertEquals("[Kiwi, Mango, Lemon, Banana]", mutableFruits.toString());
  }

}
