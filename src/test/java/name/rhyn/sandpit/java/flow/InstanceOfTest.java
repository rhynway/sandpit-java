package name.rhyn.sandpit.java.flow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class InstanceOfTest {

  @Test
  void basicInstanceOfWith() {
    assertEquals(10, basicInstanceOf("Hello"));
    assertEquals(20, basicInstanceOf(LocalDate.now()));
  }

  private int basicInstanceOf(Object object) {
    if (object instanceof String) {
      return 10;
    } else if (object instanceof LocalDate) {
      return 20;
    } else {
      return 0;
    }
  }

  @Test
  void patternInstanceOfWith() {
    LocalDate aDay = LocalDate.of(2024, 9, 1);
    assertEquals("LocalDate 2024-09-01", patternMatchingInstanceOf(aDay));
    assertEquals("Long 13", patternMatchingInstanceOf(13L));
    assertEquals("Longer text: Learn Java", patternMatchingInstanceOf("Learn Java"));
    assertEquals("Not detected", patternMatchingInstanceOf(new Object()));
  }

  private String patternMatchingInstanceOf(Object object) {
    if (object instanceof LocalDate date) {
      return "LocalDate " + date;
    } else if (object instanceof Long longer) {
      return "Long " + longer.hashCode();
    } else if (object instanceof String text && text.length() > 5) {
      return "Longer text: " + text;
    } else {
      return "Not detected"; // date can't be referenced
    }
  }

}