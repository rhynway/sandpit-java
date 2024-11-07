package name.rhyn.sandpit.java.datatypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * All primitive data type wrappers are immutable
 */
class DataTypesTest {

  boolean notInitialized;  // Default is false

  @Test
  void typeBoolean() {
    boolean localNotInitialized;  // Local variables must be initialized before referring
    boolean initialized = true;
    // Wrapper class
    Boolean booleanWrapper = initialized;

    assertFalse(notInitialized);
    assertTrue(initialized);
    assertTrue(booleanWrapper);
  }

  @Test
  void typeChar() {
    // 16-bit, Unicode character from 0 to 65535
    char japaneseSu = '\u3059'; // unicode sequence
    char euroSign = '\u20AC'; // unicode sequence
    char j = '\112'; // octal

    // Wrapper
    assertEquals('す', japaneseSu);
    assertEquals('€', euroSign);
    assertEquals('J', j);
    Character charWrapper = japaneseSu;
    assertEquals('す', charWrapper);
  }

  @Test
  void typeByte() {
    // 8-bit, -128 to 127
    byte byteValue = 6;

    byte twentyFour = 0b00011000; // 0b or 0B used as prefix to define a byte

    // Wrapper
    Byte byteWrapper = byteValue;
    short s = 3;
    int byteAndShort = byteValue + s;
    int characterA = 'a';
    int byteAndChar = byteValue + 'a'; // Promotion occurs before the addition

    assertEquals(6, byteValue);
    assertEquals(24, twentyFour);
    assertEquals(6, byteWrapper.byteValue());
    assertEquals(9, byteAndShort);
    assertEquals(97, characterA);
    assertEquals(103, byteAndChar);
  }

  @Test
  void typeShort() {
    // 16-bit, signed integer -32,768 to 32,767
    short s = 3;

    // Wrapper
    Short shortWrapper = s;

    assertEquals(3, s);
    assertEquals("3", shortWrapper.toString());
  }

  @Test
  void typeInt() {
    // 32-bit, signed integer
    int bigNumber = 1_234_567_890;

    int tenDefinedBinary = 0B00001010; // 10
    int oneHundredDefinedOctal = 0144; // Defined with a leading zero
    int oneThousandDefinedHexadecimal = 0x3E8; // 0x or 0X

    assertEquals(10, tenDefinedBinary);
    assertEquals(100, oneHundredDefinedOctal);
    assertEquals(1000, oneThousandDefinedHexadecimal);

    // Wrapper
    Integer bigNumberWrapper = bigNumber;

    assertEquals(1_234_567_890, bigNumber);
    assertEquals(1_234_567_890, bigNumberWrapper);
    assertEquals(2147483647, Integer.MAX_VALUE);
    assertEquals(-2147483648, Integer.MIN_VALUE);

    Integer i2 = 99;
    i2++; // Unwrapping is applied before increment
    int i3 = i2;
    long l1 = i2;
    Object o = i3;  // Will do autoboxing

    assertEquals(100, o);
    Integer f = Integer.valueOf("F", 16);   // Convert from hexadecimal to decimal
    assertEquals(15, f);
  }

  @Test
  void typeLong() {
    // 64-bit, signed integer -9.2e18 to 9.2e18
    long evenBiggerNumber = 111_222_333_444_555_666L; // L or l

    // Wrapper
    Long evenBiggerNumberWrapper = evenBiggerNumber;
    assertEquals(Long.valueOf(111222333444555666L), evenBiggerNumberWrapper);
  }

  @Test
  void typeFloat() {
    // 32-bit, IEEE 754, floating-point value 6-7 significant decimal places
    float moreThanThreeHundred = 333.445567f; // F or f

    // Wrapper
    Float moreThanThreeHundredWrapper = moreThanThreeHundred;
    assertEquals("333.44556", moreThanThreeHundredWrapper.toString());
    Float oneHundred = Float.valueOf("100");
    assertEquals("100.0", oneHundred.toString());

    float f1 = Float.parseFloat("Infinity");
    float f2 = Float.parseFloat("+Infinity");
    float f3 = Float.parseFloat("-Infinity");
    float f4 = Float.parseFloat("NaN");

    assertEquals(Float.POSITIVE_INFINITY, f1);
    assertEquals(Float.POSITIVE_INFINITY, f2);
    assertEquals(Float.NEGATIVE_INFINITY, f3);
    assertEquals(Float.NaN, f4);
  }

  @Test
  void typeDouble() {
    // 64-bit, IEEE 754 15 significant decimal places
    double almostOneMillion = 999999.3342424;

    // Wrapper
    Double almostOneMillionWrapper = almostOneMillion;
    assertEquals("999999.3342424", almostOneMillionWrapper.toString());

    double d = 301.999;
    String hexString = Double.toHexString(d);
    assertEquals("0x1.2dffbe76c8b44p8", hexString);

    double d1 = Double.parseDouble("Infinity");
    double d2 = Double.parseDouble("+Infinity");
    double d3 = Double.parseDouble("-Infinity");
    double d4 = Double.parseDouble("NaN");

    assertEquals(Double.POSITIVE_INFINITY, d1);
    assertEquals(Double.POSITIVE_INFINITY, d2);
    assertEquals(Double.NEGATIVE_INFINITY, d3);
    assertEquals(Double.NaN, d4);
  }

}
