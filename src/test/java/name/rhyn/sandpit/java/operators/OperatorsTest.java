package name.rhyn.sandpit.java.operators;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Operators ordered from highest to lowest precedence
 * - Postfix              ++ --
 * - Prefix / unary       ++ -- + - ~ !
 * - Multiplicative       * / %
 * - Additive             + -
 * - Shift                << >> >>>
 * - Relational           < > <= >= instanceof
 * - Equality             == !=
 * - Bitwise and logical  & ^ | && || (precedence left to right)
 * - Conditional          ? :
 * - Assignment           = += -= *= /= %= &= ^= |= <<= >>= >>>=
 */
class OperatorsTest {

  @Test
  void postfixIncrement() {
    int number = 5;
    int assignedNumber = number++;

    assertEquals(6, number);
    assertEquals(5, assignedNumber);
  }

  @Test
  void postFixDecrement() {
    int number = 5;
    int assignedNumber = number--;

    assertEquals(4, number);
    assertEquals(5, assignedNumber);
  }

  @Test
  void prefixIncrement() {
    // This is called a side effect
    int number = 4;
    int assignedNumber = ++number; // ++4 isn't allowed, only variables

    assertEquals(5, number);
    assertEquals(5, assignedNumber);
  }

  @Test
  void prefixDecrement() {
    int number = 4; // reset
    int assignedNumber = --number;
    assertEquals(3, number);
    assertEquals(3, assignedNumber);
  }

  @Test
  void unary() {
    var number = 8;
    var inverted = ~number; // Inverts all bits
    var sameNumber = ~inverted;

    assertEquals(-9, inverted);
    assertEquals(8, sameNumber);

    int bitwiseComplement = ~10;

    assertEquals(-11, bitwiseComplement);

    // The invert operator doesn't work on boolean
  }

  @Test
  void logicalComplement() {
    boolean notActive = false;
    boolean active = !notActive;

    assertTrue(active);
  }

  @Test
  void multiply() {
    int multiplied = 3 * 5;

    assertEquals(15, multiplied);
  }

  @Test
  void divided() {
    int divided = 10 / 2;

    assertEquals(5, divided);
  }

  @Test
  void modulo() {
    int remainder = 15 % -4;
    int sameRemainder = 15 % 4;

    assertEquals(3, remainder);
    assertEquals(3, sameRemainder);

    int negativeRemainder = -8 % 3; // The sign comes from the left hand sign

    assertEquals(-2, negativeRemainder);
  }

  @Test
  void additive() {
    var number = 8;
    var addResult = number + 2;
    var subtractResult = number - 2;

    assertEquals(10, addResult);
    assertEquals(6, subtractResult);
  }

  @Test
  void shiftLeft() {
    int number = 8;
    assertEquals("1000", Integer.toBinaryString(number));
    int shiftedLeft = 8 << 2;

    assertEquals(32, shiftedLeft);
    assertEquals("100000", Integer.toBinaryString(shiftedLeft));
  }

  @Test
  void shiftRight() {
    int shiftedRight = 256 >> 4;

    assertEquals(16, shiftedRight);
    assertEquals("10000", Integer.toBinaryString(shiftedRight));
  }

  @Test
  void unsignedShiftRight() {
    short negativeNumber = -200;
    int shiftedRight = negativeNumber >>> 5; // it always results in type int

    assertEquals(134217721, shiftedRight);
    assertEquals("111111111111111111111111001", Integer.toBinaryString(shiftedRight));
  }

  @Test
  void relational() {
    short a = 4;
    short b = 7;
    boolean smallerThanOrEqual = a <= b;

    assertTrue(smallerThanOrEqual);

    String text = "Some text";
    assertTrue(text instanceof Object);
    assertTrue(text instanceof String);
  }

  @Test
  void equality() {
    short three = 3;
    short four = 4;

    assertTrue(three != four);
    assertTrue(three == 3);
  }

  @Test
  void bitwise() {
    int ten = 10;
    int six = 6;
    assertEquals("1010", Integer.toBinaryString(ten));
    assertEquals("110", Integer.toBinaryString(six));
    int bitwiseAnd = ten & six;

    assertEquals(2, bitwiseAnd);
    assertEquals("10", Integer.toBinaryString(bitwiseAnd));

    byte fiftySeven = 57;
    assertEquals("111001", Integer.toBinaryString(fiftySeven));
    byte mask = 0xF;
    assertEquals("1111", Integer.toBinaryString(mask));
    int masked = fiftySeven & mask;

    assertEquals(9, masked);
    assertEquals("1001", Integer.toBinaryString(masked));

    boolean secondBitwiseAnd = 1 < 2 & 4 < 6; // Both expressions are evaluated

    assertTrue(secondBitwiseAnd);

    int bitwiseXor = 8 ^ 4;

    assertEquals(12, bitwiseXor);

    int bitwiseOr = 8 | 4;

    assertEquals(12, bitwiseOr);
  }

  @Test
  void logical() {
    // Short-circuit evaluaction
    boolean logicalAnd = false && 5 > 3; // Second operand isn't evaluated.

    assertFalse(logicalAnd);

    // Short-circuit evaluaction
    boolean logicalOr = true || 5 > 3; // Second operand isn't evaluated.

    assertTrue(logicalOr);
  }

  @Test
  void conditional() {
    String ternaryOperator = 2 < 3 ? "Yes" : "No";

    assertEquals("Yes", ternaryOperator);
  }

  @Test
  void assignment() {
    // General form <l-value> <op>= <expression>
    int a = 2, b = 2, c = 2;

    b += a;
    c -= 1;

    assertEquals(2, a);
    assertEquals(4, b);
    assertEquals(1, c);

    int d = 4, e = 2;
    d <<= e;

    assertEquals(16, d);

    int x = 12 / 3 / 2; // from left to right

    assertEquals(2, x);

    int[] numbers = {4, 5, 6, 7};
    int[] assignedNumbers = numbers;
    int position = 0;
    assertEquals(0, position);

    assertArrayEquals(assignedNumbers, numbers);
    assertEquals(7, assignedNumbers[position = 3]);
    assertEquals(3, position);

    boolean charOrInt = true;
    int result = charOrInt ? 'a' : 99;

    assertEquals('a', result);

    Integer amount = 12;
    Double bigAmount = 80000d;
    double upsized = 1 < 2 ? amount : bigAmount;

    assertEquals(12, upsized);

    short s = 10;
    byte bigest = 127;
    short s2 = true ? bigest : s;

    final int i = 10;
    byte downsized = true ? bigest : i; // Downsizing only with int

    assertEquals(127, downsized);

    String text = "Hello";
    StringBuilder sb = new StringBuilder();

    Object o = true ? text : sb;
    assertEquals(text, o);
  }

  @Test
  void operatorsRepeated() {
    // Work with one or more boolean
    boolean resultXor = true ^ false ^ true;
    boolean resultAnd = true & true & false;
    boolean resultOr = true || false || false;

    assertFalse(resultXor);
    assertFalse(resultAnd);
    assertTrue(resultOr);
  }

}
