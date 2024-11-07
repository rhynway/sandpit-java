package name.rhyn.sandpit.java.referencetypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * The StringBuilder class is mutable
 */
class StringBuilderTest {

  @Test
  void defaultCapacity() {
    var stringBuilder = new StringBuilder();

    assertEquals(16, stringBuilder.capacity());
  }

  @Test
  void initializeCapacity() {
    var stringBuilder = new StringBuilder(30);

    assertEquals(30, stringBuilder.capacity());
  }

  @Test
  void ensureCapacity() {
    var stringBuilder = new StringBuilder();
    assertEquals(16, stringBuilder.capacity());
    stringBuilder.ensureCapacity(20);

    assertEquals(34, stringBuilder.capacity());
  }

  @Test
  void methodTrimToSize() {
    var stringBuilder = new StringBuilder("I learn Java");

    assertEquals(28, stringBuilder.capacity());
    stringBuilder.trimToSize();
    assertEquals(12, stringBuilder.capacity());
  }

  @Test
  void methodAppend() {
    var stringBuilder = new StringBuilder()
        .append(13)
        .append(new char[]{'a', 'b', 'c'})
        .append("Hello", 0, 5);

    assertEquals("13abcHello", stringBuilder.toString());
  }

  @Test
  void methodInsert() {
    var stringBuilder = new StringBuilder("123456");

    stringBuilder.insert(2, " ")
        .insert(6, " - ");

    assertEquals("12 345 - 6", stringBuilder.toString());
  }

  @Test
  void methodReplace() {
    var stringBuilder = new StringBuilder("123456");

    var replaced = stringBuilder.replace(2, 4, "-");

    assertEquals("12-56", replaced.toString());
  }

  @Test
  void methodDeleteAndDeleteCharAt() {
    var stringBuilder = new StringBuilder("123456");

    StringBuilder twoDeleted = stringBuilder.deleteCharAt(1)
        .delete(4, 5)
        .deleteCharAt(2);

    assertEquals("135", twoDeleted.toString());
  }

  @Test
  void methodSubstringAndCharAt() {
    var stringBuilder = new StringBuilder("ABC-123");

    assertEquals("12", stringBuilder.substring(4, 6));
    assertEquals('C', stringBuilder.charAt(2));
  }

  @Test
  void methodIndexOf() {
    var stringBuilder = new StringBuilder("123");

    // When not found
    assertEquals(-1, stringBuilder.indexOf("c"));
    assertEquals(2, stringBuilder.indexOf("3"));
  }

  @Test
  void methodEquals() {
    String s = "Mein Name ist Peter";
    StringBuilder sb3 = new StringBuilder(s);
    StringBuilder sb4 = new StringBuilder(s);

    assertFalse(sb3.equals(sb4));
  }

  @Test
  void reverse() {
    var stringBuilder = new StringBuilder("This is a StringBuilder");

    StringBuilder reversed = stringBuilder.reverse();
    assertEquals("redliuBgnirtS a si sihT", reversed.toString());
  }

}
