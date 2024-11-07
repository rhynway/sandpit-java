package name.rhyn.sandpit.java.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import name.rhyn.sandpit.java.datatypes.ClothingSize;
import org.junit.jupiter.api.Test;

/**
 * Map, Dictionary or Associative Array
 */
class MapTest {

  private static final String PERSON_ONE = "Person 1";
  private static final String PERSON_TWO = "Person 2";
  private static final String PERSON_THREE = "Person 3";
  private static final String PERSON_FOUR = "Person 4";
  private static final String PERSON_FIVE = "Person 5";

  /**
   * HashMap is not ordered and not sorted. The maps key must overwrite equals and hashCode to work
   * correctly
   */
  @Test
  void hashMap() {
    Map<String, Integer> points = new HashMap<>();
    points.put(PERSON_ONE, 3401);
    points.put(PERSON_TWO, 7345);
    points.putIfAbsent(PERSON_THREE, 2323);
    points.computeIfAbsent(PERSON_FOUR, (value) -> 1287);

    assertEquals(4, points.size());
    assertEquals(7345, points.get(PERSON_TWO));
    assertEquals(1000, points.getOrDefault("Wrong", 1000));
    assertTrue(points.replace(PERSON_TWO, 7345, 7777));
    assertTrue(points.remove(PERSON_TWO, 7777));

    Integer merged = points.merge(PERSON_ONE, -1000, Integer::sum); // BiFunction
    assertEquals(2401, merged);
    assertEquals(merged, points.get(PERSON_ONE));

    Integer updatedValue = points.computeIfPresent(PERSON_THREE, (key, value) -> value + 7);
    assertEquals(2330, points.get(PERSON_THREE));
  }

  @Test
  void nullAsKey() {
    Map<String, Integer> points = new HashMap<>();
    points.put(null, 99);

    assertEquals(1, points.size());
    assertEquals(99, points.get(null));
  }

  @Test
  void nullAsValue() {
    Map<String, Integer> points = new HashMap<>();
    points.put("A", null);

    assertEquals(1, points.size());
    assertEquals(null, points.get("A"));
  }

  @Test
  void enumMap() {
    EnumMap<ClothingSize, String> translations = new EnumMap<>(ClothingSize.class);
    translations.put(ClothingSize.SMALL, "Klein");
    translations.put(ClothingSize.MEDIUM, "Normal");
    translations.put(ClothingSize.LARGE, "Gross");

    assertEquals(3, translations.size());
  }

  @Test
  void treeMap() {
    // TreeMap is sorted. Ordered sorted by natural order or custom comparison
    var personPoints = new TreeMap<String, Integer>();
    personPoints.put(PERSON_ONE, 23);
    personPoints.put(PERSON_TWO, 87);
    personPoints.put(PERSON_THREE, 45);
    personPoints.put(PERSON_FOUR, 9);
    personPoints.put(PERSON_FIVE, 101);

    assertEquals("{Person 1=23, Person 2=87, Person 3=45, Person 4=9, Person 5=101}",
        personPoints.toString());

    assertEquals(PERSON_FOUR, personPoints.higherKey(PERSON_THREE));
    assertEquals(PERSON_THREE, personPoints.ceilingKey(PERSON_THREE));
    assertEquals(PERSON_TWO, personPoints.lowerKey(PERSON_THREE));

    Map<String, Integer> headMap = personPoints.headMap(PERSON_THREE);
    assertEquals("{Person 1=23, Person 2=87}", headMap.toString());

    Map<String, Integer> headMap2 = personPoints.headMap(PERSON_THREE, true);
    assertEquals("{Person 1=23, Person 2=87, Person 3=45}", headMap2.toString());

    Map.Entry<String, Integer> five = personPoints.floorEntry(PERSON_THREE);
    assertEquals(45, five.getValue());

    Map.Entry<String, Integer> first = personPoints.pollFirstEntry();
    assertEquals(PERSON_ONE, first.getKey());
    assertEquals(23, first.getValue());
    assertEquals(4, personPoints.size());

    Map.Entry<String, Integer> last = personPoints.pollLastEntry();
    assertEquals(PERSON_FIVE, last.getKey());
    assertEquals(101, last.getValue());
    assertEquals(3, personPoints.size());
  }

  @Test
  void mapOf() {
    Map<Integer, Character> immutableCharacters = Map.of(1, 'A', 2, 'B', 3, 'C');

    assertEquals(3, immutableCharacters.size());
    assertThrows(UnsupportedOperationException.class, () -> immutableCharacters.put(4, 'D'));
  }

}
