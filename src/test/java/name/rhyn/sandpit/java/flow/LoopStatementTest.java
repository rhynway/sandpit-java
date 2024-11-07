package name.rhyn.sandpit.java.flow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class LoopStatementTest {

  @Test
  void cStyleForLoop() {
    var results = new ArrayList<Integer>();
    int[] ints = {2, 4, 6, 8};

    for (int i = 0, a = 5; i < ints.length; i++) {
      results.add(ints[i]);
      if (i == 1) {
        results.add(a);
      }
    }

    assertEquals(List.of(2, 4, 5, 6, 8), results);

    // Either variable declaration as show above or expression
    int loopValue;
    for (loopValue = 0, "Test".length(), ++loopValue; loopValue < 3; loopValue++) {

    }
    assertEquals(3, loopValue);

    for (; loopValue < 5; ) {
      loopValue++;
    }
    assertEquals(5, loopValue);
  }

  @Test
  void cStyleForLoopComplex() {
    var results = new ArrayList<Integer>();

    // The variables must be of the same type
    for (int u = 0, v = 1, w, x = 13; v < 3; ++u, v++, System.out.println("update " + v)) {
      results.add(v);
      if (v == 2) {
        w = 7;
        results.add(w);
        results.add(x);
      }
    }

    assertEquals(List.of(1, 2, 7, 13), results);
  }

  @Test
  void enhancedForLoop() {
    var results = new ArrayList<>();
    int[] ints2 = {2, 4, 6, 8};
    for (int i : ints2) { // The "bucket" element must be an Iterable or array
      results.add("A_" + i);
    }

    assertEquals(List.of("A_2", "A_4", "A_6", "A_8"), results);

    var result = new StringBuilder();
    var characters = List.of('A', 'B', 'C', 'D');
    for (Character character : characters) {
      result.append(character);
    }
    assertEquals("ABCD", result.toString());
  }

  @Test
  void whileLoop() {
    var results = new ArrayList<>();
    int i = 0;
    // The expression in brackets must be a boolean expression
    while (i < 5) {
      results.add(i);
      ++i;
    }

    assertEquals(List.of(0, 1, 2, 3, 4), results);
  }

  @Test
  void doWhileLoop() {
    var results = new ArrayList<>();
    int i = 3;
    do {
      results.add(i);
      --i;
    } while (i > 0); // Here the semicolon is required

    assertEquals(List.of(3, 2, 1), results);
  }

  @Test
  void breakLoop() {
    var results = new ArrayList<>();
    for (int i = 4; i > 0; i--) {
      if (i == 2) {
        break; // Stops the processing of the loop
      }
      results.add(i);
    }

    assertEquals(List.of(4, 3), results);
  }

  @Test
  void continueLoop() {
    var results = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      if (i % 3 == 0) {
        continue;
      }
      results.add(i);
    }

    assertEquals(List.of(1, 2, 4, 5, 7, 8), results);
  }

  @Test
  void labeledLoopWithContinue() {
    var results = new ArrayList<String>();

    for (int i = 0; i < 4; i++) {
      outer:
      for (int j = 0; j < 4; j++) {
        if (i != j) {
          continue outer;
        }
        inner:
        for (int k = 0; k < 4; k++) {
          if (j != k) {
            continue inner;
          }
          results.add(i + " " + j + " " + k);
        }
      }
    }

    assertEquals(List.of("0 0 0", "1 1 1", "2 2 2", "3 3 3"), results);
  }

  @Test
  void labeledLoopWithBreak() {
    var results = new ArrayList<>();

    outer:
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (i != j) {
          continue;
        }
        if (i + j > 6) {
          break outer;
        }
        results.add(i + " " + j);
      }
    }

    assertEquals(List.of("0 0", "1 1", "2 2", "3 3"), results);
  }

  @Disabled
  @Test
  void infiniteDoLoop() {
    do {
    } while (true);
  }

  @Disabled
  @Test
  void infiniteForLoop() {
    for (; ; ) {

    }
  }

  @Disabled
  @Test
  void infiniteEnhancedForLoop() {
    Iterable<Integer> interable = () -> new Iterator<>() {
      public boolean hasNext() {
        return true;
      }

      public Integer next() {
        return 1; // or any constant value
      }
    };

    for (int i : interable) {
    }
  }

  @Disabled
  @Test
  void infiniteWhileLoops() {
    while (true) {
    }
  }

}
