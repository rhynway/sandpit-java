package name.rhyn.sandpit.java.flow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ConditionalStatementTest {

  @Test
  void ifStatement() {
    var text = "Some text";

    if (text.length() > 8) {
      assertEquals(9, text.length());
    }
  }

  @Test
  void ifElseStatement() {
    String result;
    int x = 3;

    if (x < 5) {
      result = "A";
    } else if (x < 8) {
      result = "B";
    } else {
      result = "C";
    }

    assertEquals("A", result);
  }

}
