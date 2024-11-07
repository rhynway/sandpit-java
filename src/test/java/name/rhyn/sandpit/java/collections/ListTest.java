package name.rhyn.sandpit.java.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ListTest {

  @Test
  void arrayList() {
    List<Object> objects = new ArrayList<>();
    objects.add(new Object());
    objects.add(null);
    objects.add(LocalDate.of(2024, 2, 4));

    assertEquals(3, objects.size());

    List<? super RuntimeException> exceptions = new ArrayList<Exception>();
    exceptions.add(new IllegalArgumentException());

    assertEquals(1, exceptions.size());
  }


  /**
   * - Insertion and deletion is generally faster than in a ArrayList
   * - Memory overhead because of the two references in each node
   */
  @Test
  void linkedList() {
    LinkedList<Character> data = new LinkedList<>(); // implements List and Deque
    data.add('A');
    data.addFirst('B');
    data.push('C');
    data.offer(null);

    assertEquals("[C, B, A, null]", data.toString());
  }

  @Test
  void listOf() {
    var characters = List.<Character>of('A', 'B', 'C');

    assertEquals(3, characters.size());

    // A maximum of 10 specific parameters are defined. With the 11 it's using var args
    var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    assertEquals(10, numbers.size());
  }

  @Test
  void typeErasure() {
    Object list = new ArrayList<String>();

    assertTrue(list instanceof List);
    // assertTrue(list instanceof List<String>); Not possible

    List<String> texts = (List) list;
    List<String> textsTwo = (List<String>) list; // String makes no difference
  }

  @Test
  void importantMethods() {
    LinkedList<Character> linkedList = new LinkedList<>();// Ordered by index, but not sorted

    linkedList.add('a');            // Add the string to the end of the list
    linkedList.addFirst('c');   // Add the string to the beginning of the list

    linkedList.offer('b');      // Add the string to the end of the list
    linkedList.offerFirst('d'); // Add the string to the beginning of the list

    linkedList.push('e');       // Add the string to the beginning of the list

    assertEquals("[e, d, c, a, b]", linkedList.toString());

    assertEquals("[d, c]", linkedList.subList(1, 3).toString());
    assertEquals('e', linkedList.peek());
    assertEquals('b', linkedList.peekLast());

    assertEquals('e', linkedList.element()); // Returns the first element. Doesn't remove it

    assertEquals('e', linkedList.poll());   // Removes first string
    assertEquals('b', linkedList.pollLast());   // Removes last string
    assertEquals('d', linkedList.pop());        // Removes first string
    assertEquals('c', linkedList.remove());     // Removes first string
    assertEquals('a', linkedList.remove(0));    // Removes string at index
    assertEquals(0, linkedList.size());
  }

  @Test
  void copyOf() {
    List<Character> immutableCharacters = List.of('X', 'Y', 'Z');
    assertThrows(UnsupportedOperationException.class, () -> immutableCharacters.add('E'));

    List<Character> sameReference = List.copyOf(
        immutableCharacters); // copyOf returned the same reference

    assertTrue(immutableCharacters == sameReference);

    List<Character> mutableCharacters = new ArrayList<>();
    mutableCharacters.add('X');
    mutableCharacters.add('Y');
    mutableCharacters.add('Z');

    List<Character> notSameList = List.copyOf(mutableCharacters); // copyOf made a copy
    assertFalse(mutableCharacters == notSameList);
  }

}
