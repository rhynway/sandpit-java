package name.rhyn.sandpit.java.generics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class GenericsTest {

  @Test
  void genericClass() {
    Key<Integer> id = new Key<>("id", Integer.class);
    Key<String> name = new Key<>("name", String.class);

    Container container = new Container();
    container.put(id, 34); // put and get from Container are generic methods
    container.put(name, "Tester");

    assertEquals(34, container.get(id));
    assertEquals("Tester", container.get(name));
  }

  // Bounded Type Parameters
  public <T extends Number> double sum(T a, T b) {
    return a.doubleValue() + b.doubleValue();
  }

  @Test
  void boundedTypeParameters() {
    assertEquals(5.0, sum(2, 3));
    assertEquals(5.5, sum(2.5, 3.0));
  }

  // Wildcard Types
  public void printList(List<?> list) {
    for (Object entry : list) {
      System.out.println(entry);
    }
  }

  @Test
  void wildcardTypes() {
    List<Integer> intList = new ArrayList<>();
    intList.add(1);
    intList.add(2);
    intList.add(3);

    List<String> strList = new ArrayList<>();
    strList.add("A");
    strList.add("B");
    strList.add("C");

    printList(intList);
    printList(strList);
  }

  // Type Erasure
  @Test
  void typeErasure() {
    List<String> stringList = new ArrayList<>();
    List<Integer> integerList = new ArrayList<>();

    assertTrue(stringList.getClass() == integerList.getClass());
  }

}
